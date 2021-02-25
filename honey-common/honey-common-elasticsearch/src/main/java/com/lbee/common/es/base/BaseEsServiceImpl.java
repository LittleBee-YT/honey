package com.lbee.common.es.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lbee.common.es.annotations.EsDocument;
import com.lbee.common.es.annotations.EsField;
import com.lbee.common.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
public class BaseEsServiceImpl<T extends BaseEsEntity, ID> {

    protected Class<T> entityClass;

    protected String indexName;
    protected int shards;
    protected int replicas;
    protected String mappingId;

    public BaseEsServiceImpl() {
        EsDocument annotation = resolveReturnedClassFromGenericType().getAnnotation(EsDocument.class);
        this.indexName = annotation.indexName();
        this.mappingId = annotation.mappingId();
        this.shards = annotation.shards();
        this.replicas = annotation.replicas();
    }

    /**
     * 创建索引
     */
    public void createIndex() {
        log.info("es 创建索引:{}", getIndexName());
        try {
            if (this.isExistsIndex()) {
                log.error(" idxName={} 已经存在", getIndexName());
                return;
            }
            CreateIndexRequest request = new CreateIndexRequest(getIndexName());
            buildSetting(request);
            buildMapping(request);
            CreateIndexResponse res = restHighLevelClient().indices().create(request, RequestOptions.DEFAULT);
            if (!res.isAcknowledged()) {
                throw new RuntimeException("初始化失败");
            } else {
                log.info("es 索引创建成功: {}", getIndexName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * 删除索引
     */
    public void deleteIndex() {
        deleteIndex(getIndexName());
    }

    /**
     * 删除索引
     *
     * @param idxName
     */
    protected void deleteIndex(String idxName) {
        try {
            if (!this.isExistsIndex()) {
                log.error("DELETE Index error, idxName={} 不存在.", idxName);
                return;
            }
            restHighLevelClient().indices().delete(new DeleteIndexRequest(idxName), RequestOptions.DEFAULT);
            log.info("DELETE Index [{}].", idxName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 索引是否存在
     *
     * @return
     * @throws Exception
     */
    public boolean isExistsIndex() throws Exception {
        return isExistsIndex(getIndexName());
    }

    /**
     * 索引是否存在
     *
     * @param idxName
     * @return
     * @throws Exception
     */
    protected boolean isExistsIndex(String idxName) throws Exception {
        return restHighLevelClient().indices().exists(new GetIndexRequest(idxName), RequestOptions.DEFAULT);
    }

//    /**
//     * 判断索引是否存在
//     *
//     * @return
//     * @throws Exception
//     */
//    public boolean indexExist() throws Exception {
//        return indexExist(getIndexName());
//    }
//
//    /**
//     * 判断Index是否存在
//     *
//     * @param idxName
//     * @return
//     * @throws Exception
//     */
//    public boolean indexExist(String idxName) throws Exception {
//        GetIndexRequest request = new GetIndexRequest(idxName);
//        request.local(false);
//        request.humanReadable(true);
//        request.includeDefaults(false);
//        request.indicesOptions(IndicesOptions.lenientExpandOpen());
//        return restHighLevelClient().indices().exists(request, RequestOptions.DEFAULT);
//    }


    /**
     * 保存对象
     *
     * @param entity
     */
    public void index(T entity) {
        IndexRequest request = new IndexRequest(indexName);
        request.id(entity.getId());
        request.type("_doc");
        request.source(JSONObject.toJSONString(entity), XContentType.JSON);
//        request.source(entity, XContentType.JSON);
        try {
            restHighLevelClient().index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存对象(异步)
     *
     * @param entity
     */
    public void indexAsync(T entity) {
        IndexRequest request = new IndexRequest();
        request.id(entity.getId());
        ActionListener listener = new ActionListener() {
            @Override
            public void onResponse(Object o) {
            }

            @Override
            public void onFailure(Exception e) {
            }
        };
        try {
            restHighLevelClient().indexAsync(request, RequestOptions.DEFAULT, listener);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 批量插入
     *
     * @param list
     */
    public void insertBatch(List<T> list) {
        BulkRequest request = new BulkRequest();
        list.forEach(item -> request.add(new IndexRequest(getIndexName()).id(item.getId())
                .source(JSONObject.toJSONString(item), XContentType.JSON)));
        try {
            restHighLevelClient().bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 批量删除
     *
     * @param idList
     */
    public void deleteBatch(Collection<T> idList) {
        BulkRequest request = new BulkRequest();
        idList.forEach(item -> request.add(new DeleteRequest(getIndexName(), item.toString())));
        try {
            restHighLevelClient().bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据条件删除
     *
     * @param builder
     */
    public void deleteByQuery(QueryBuilder builder) {
        DeleteByQueryRequest request = new DeleteByQueryRequest(getIndexName());
        request.setQuery(builder);
        //设置批量操作数量,最大为10000
        request.setBatchSize(10000);
        request.setConflicts("proceed");
        try {
            restHighLevelClient().deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询
     *
     * @param builder
     * @return
     */
    public List<T> search(SearchSourceBuilder builder) {
        SearchRequest request = new SearchRequest(getIndexName());
        request.source(builder);
        try {
            SearchResponse response = restHighLevelClient().search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            List<T> res = new ArrayList<>(hits.length);
            for (SearchHit hit : hits) {
                res.add(JSON.parseObject(hit.getSourceAsString(), getEntityClass()));
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置分片&副本
     *
     * @param request
     */
    private void buildSetting(CreateIndexRequest request) {
        request.settings(Settings.builder().put("index.number_of_shards", shards)
                .put("index.number_of_replicas", replicas));
    }

    /**
     * 设置Mapping
     *
     * @param request
     */
    private void buildMapping(CreateIndexRequest request) {
        JSONObject properties = new JSONObject();
        Field[] fields = getAllFields(getEntityClass());
        Arrays.stream(fields).forEach(field -> {
            EsField annotation = field.getAnnotation(EsField.class);
            if (null != annotation) {  //判断字段是否包含EsField注解
                String name = StringUtils.isEmpty(annotation.name()) == true ? field.getName() : annotation.name();
                String type = StringUtils.isEmpty(annotation.type()) == true ? "text" : annotation.type();
                boolean fielddata = annotation.fielddata();
                int dims = annotation.dims();
                JSONObject attribute = new JSONObject();
                attribute.put("type", type);
                if (fielddata) {
                    attribute.put("fielddata", fielddata);
                }
                if (dims != 0) {
                    attribute.put("dims", dims);
                }
                properties.put(name, attribute);
            }
        });
        JSONObject json = new JSONObject();
        json.put("properties", properties);
        log.info(json.toJSONString());
        request.mapping(json.toJSONString(), XContentType.JSON);
    }

    private Field[] getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    public RestHighLevelClient restHighLevelClient() {
        return (RestHighLevelClient) SpringUtils.getObject("restHighLevelClient");
    }

    @SuppressWarnings("unchecked")
    private Class<T> resolveReturnedClassFromGenericType() {
        ParameterizedType parameterizedType = resolveReturnedClassFromGenericType(getClass());
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    private ParameterizedType resolveReturnedClassFromGenericType(Class<?> clazz) {
        Object genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            return parameterizedType;
        }
        return resolveReturnedClassFromGenericType(clazz.getSuperclass());
    }

    protected Class<T> getEntityClass() {
        if (null == entityClass) {
            try {
                this.entityClass = resolveReturnedClassFromGenericType();
            } catch (Exception e) {
                throw new RuntimeException("Unable to resolve EntityClass. Please use according setter!", e);
            }
        }
        return entityClass;
    }

    protected String getIndexName() {
        return indexName;
    }

    protected String getMappingId() {
        return mappingId;
    }
}
