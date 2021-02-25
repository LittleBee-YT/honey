package com.lbee.common.es.base;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Collection;
import java.util.List;

public interface BaseEsService<T, ID> {

    /**
     * 创建索引
     */
    void createIndex();

    /**
     * 删除索引
     */
    void deleteIndex();

    /**
     * 判断索引是否存在
     *
     * @return
     * @throws Exception
     */
    boolean isExistsIndex() throws Exception;

    /**
     * 插入
     * @param entity
     */
    void index(T entity);

    /**
     * 插入（异步）
     * @param entity
     */
    void indexAsync(T entity);

    /**
     * 批量插入
     * @param list
     */
    void insertBatch(List<T> list);

    /**
     * 批量删除
     * @param idList
     */
    void deleteBatch(Collection<T> idList);

    /**
     * 条件删除
     * @param builder
     */
    void deleteByQuery(QueryBuilder builder);

    /**
     * 条件查询
     * @param builder
     * @return
     */
    List<T> search(SearchSourceBuilder builder);

}
