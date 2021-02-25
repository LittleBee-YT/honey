package com.lbee.generator.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;

import com.lbee.generator.dto.TableColumnDTO;
import com.lbee.generator.entity.TableColumn;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public class GeneratorUtil {

    private final String ENTITY_JAVA_FTL = "Entity.java.ftl";
    private final String VO_JAVA_FTL = "VO.java.ftl";
    private final String MAPPER_JAVA_FTL = "Mapper.java.ftl";
    private final String SERVICE_JAVA_FTL = "Service.java.ftl";
    private final String SERVICE_IMPL_JAVA_FTL = "ServiceImpl.java.ftl";
    private final String CONTROLLER_JAVA_FTL = "Controller.java.ftl";
    private final String MAPPER_XML_FTL = "Mapper.xml.ftl";
    private final String API_JS_FTL = "Api.js.ftl";
    private final String STORE_JS_FTL = "Store.js.ftl";
    private final String INDEX_VUE_FTL = "Index.vue.ftl";

    private final String TENANT_ENTITY_ATTR = "tenantId";
    private final String[] TREE_ENTITY_ATTRS = {"parentId", "parentIds", "name", "sort"};

    private List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add(ENTITY_JAVA_FTL);
        templates.add(VO_JAVA_FTL);
        templates.add(MAPPER_JAVA_FTL);
        templates.add(SERVICE_JAVA_FTL);
        templates.add(SERVICE_IMPL_JAVA_FTL);
        templates.add(CONTROLLER_JAVA_FTL);
        templates.add(MAPPER_XML_FTL);
//        templates.add("ForeApi.js.ftl");
//        templates.add("ForeStore.js.ftl");
//        templates.add("ForeIndex.vue.ftl");
        return templates;
    }

    @SneakyThrows
    public static void generatorCode(String project, String moduleName, String tableName, String packageName, String className,
                                     String comments, List<TableColumn> tableColumns) {

        List<TableColumnDTO> dtoList = tableColumns.stream().map(c -> {
            TableColumnDTO dto = new TableColumnDTO();
            BeanUtils.copyProperties(c, dto);
            String lowerAttrName = StrUtil.lowerFirst(StrUtil.toCamelCase(c.getColumnName()));
            dto.setAttrType(DataTypeUtil.getDataType(c.getDataType()));
            dto.setLowerAttrName(lowerAttrName);
            return dto;
        }).collect(Collectors.toList());

        //封装模板数据
        Map<String, Object> map = new HashMap<>();
//        map.put("author", "");
        map.put("tableName", tableName);
        map.put("comments", comments == null ? "" : comments);
        map.put("moduleName", moduleName);
        map.put("className", StrUtil.upperFirst(className));
        map.put("classname", StrUtil.lowerFirst(className));
        map.put("pathName", className.toLowerCase());
        map.put("package", packageName);
        map.put("columns", dtoList);
        map.put("datetime", DateUtil.now());

        // 加载项目配置项
        ProjectContext.getConfigMap().forEach((k, v) -> map.put(k, v));

        // 判断BaseEntity类型
        boolean isTenantEntity = false;
        int treeAttrCount = 0;

        Set<String> treeAttrs = new HashSet<>(Arrays.asList(TREE_ENTITY_ATTRS));
        for (TableColumnDTO dto : dtoList) {
            if (dto.getLowerAttrName().equals(TENANT_ENTITY_ATTR)) {
                isTenantEntity = true;
            } else if (treeAttrs.contains(dto.getLowerAttrName())) {
                treeAttrCount++;
            }
        }

        if (treeAttrCount == TENANT_ENTITY_ATTR.length()) {
            if (isTenantEntity) {
                map.put("baseEntity", "TenantTreeEntity");
            } else {
                map.put("baseEntity", "TreeEntity");
            }
        } else {
            if (isTenantEntity) {
                map.put("baseEntity", "TenantEntity");
            } else {
                map.put("baseEntity", "BaseEntity");
            }
        }

        //获取模板列表
        List<String> templates = getTemplates();

        Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());
        ClassPathResource classPathResource = new ClassPathResource("templates");
        configuration.setDirectoryForTemplateLoading(classPathResource.getFile());

        long version = FileVersion.get();

        templates.stream().forEach(template -> {
            try {
                //渲染模板
                Template tpl = configuration.getTemplate(template, CharsetUtil.UTF_8);
                String content = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map);
                String fileName = getFileName(template, className, packageName, project, moduleName);
                if (null != fileName) {
                    file(fileName, content, version);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static String getBasePage() {
        try {
//            return System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("/"));
            File directory = new File(".");
            int lastIdx = 0;
            if (directory.getCanonicalPath().lastIndexOf("/") != -1) {
                lastIdx = directory.getCanonicalPath().lastIndexOf("/"); // mac or linux
            } else {
                lastIdx = directory.getCanonicalPath().lastIndexOf("\\"); // windows
            }
            return directory.getCanonicalPath().substring(0, lastIdx);
        } catch (IOException e) {
            throw new RuntimeException("根目录路径获取失败.");
        }
    }

    private void file(String fileName, String content, long version) throws IOException {
        String filePath = getBasePage() + fileName;
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                // 创建文件
                FileUtil.createFile(filePath, content);
                log.info("生成文件：{}", filePath);
            } else {
                // 备份文件
                String fixName = fileName.substring(fileName.lastIndexOf("/") + 1) + "." + version + ".old";
                FileUtil.fixFileName(filePath, fixName);
                //创建文件
                FileUtil.createFile(filePath, content);
                log.info("生成文件：{}, 备份文件: {}", filePath, fixName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileName(String template, String className, String packageName, String project, String moduleName) {
        String apiPath = File.separator + project + File.separator + project + "-api";
        String servicePath = File.separator + project + File.separator + project + "-server";
        String webPath = project + File.separator + "web";

        String packagePath = File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator);
        }

        switch (template) {
            case ENTITY_JAVA_FTL:
                return apiPath + packagePath + File.separator + moduleName + File.separator + "entity" + File.separator + className + ".java";
            case VO_JAVA_FTL:
                return apiPath + packagePath + File.separator + moduleName + File.separator + "vo" + File.separator + className + "VO.java";
            case MAPPER_JAVA_FTL:
                return servicePath + packagePath + File.separator + moduleName + File.separator + "mapper" + File.separator + className + "Mapper.java";
            case SERVICE_JAVA_FTL:
                return servicePath + packagePath + File.separator + moduleName + File.separator + "service" + File.separator + className + "Service.java";
            case SERVICE_IMPL_JAVA_FTL:
                return servicePath + packagePath + File.separator + moduleName + File.separator + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
            case CONTROLLER_JAVA_FTL:
                return servicePath + packagePath + File.separator + moduleName + File.separator + "controller" + File.separator + className + "Controller.java";
            case MAPPER_XML_FTL:
                return servicePath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Mapper.xml";
            case API_JS_FTL:
                return webPath + "api" + File.separator + moduleName + File.separator + StrUtil.lowerFirst(className) + ".js";
            case STORE_JS_FTL:
                return webPath + "store" + File.separator + "modules" + File.separator + moduleName + File.separator + StrUtil.lowerFirst(className) + ".js";
            case INDEX_VUE_FTL:
                return webPath + "views" + File.separator + moduleName + File.separator + "index.vue";
        }
//        if (template.contains(ENTITY_JAVA_FTL)) { // .entity
//            return apiPath + packagePath + File.separator + moduleName + File.separator + "entity" + File.separator + className + ".java";
//        }

//        if (template.contains(MAPPER_JAVA_FTL)) { // .mapper
//            return servicePath + packagePath + File.separator + moduleName + File.separator + "mapper" + File.separator + className + "Mapper.java";
//        }

//        if (template.contains(SERVICE_JAVA_FTL)) {
//            return apiPath + packagePath + File.separator + moduleName + File.separator + "service" + File.separator + className + "Service.java";
//        }
//
//        if (template.contains(SERVICE_IMPL_JAVA_FTL)) {
//            return servicePath + packagePath + File.separator + moduleName + File.separator + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
//        }

//        if (template.contains(CONTROLLER_JAVA_FTL)) {
//            return servicePath + packagePath + File.separator + moduleName + File.separator + "controller" + File.separator + className + "Controller.java";
//        }
//
//        if (template.contains(MAPPER_XML_FTL)) {
//            return servicePath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Mapper.xml";
//        }
//
//        if (template.contains(API_JS_FTL)) {
//            return webPath + "api" + File.separator + moduleName + File.separator + StrUtil.lowerFirst(className) + ".js";
//        }
//
//        if (template.contains(STORE_JS_FTL)) {
//            return webPath + "store" + File.separator + "modules" + File.separator + moduleName + File.separator + StrUtil.lowerFirst(className) + ".js";
//        }
//
//        if (template.contains(INDEX_VUE_FTL)) {
//            return webPath + "views" + File.separator + moduleName + File.separator + "index.vue";
//        }

        return null;
    }


//    public static void main(String[] args) {
//        try {
//            File directory = new File(".");
//            System.out.println(directory.getPath());
//            System.out.println(directory.getAbsolutePath());
//            System.out.println(directory.getCanonicalPath());
//            System.out.println(getBasePage());
//            System.out.println(directory.getPath());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        File file = new File("/aiot-platform/aiot-platform-api/src/main/java/com/lbee/aiot/dict/entity/PublicDictionary.java");
//        System.out.println(file.exists());
//    }
}
