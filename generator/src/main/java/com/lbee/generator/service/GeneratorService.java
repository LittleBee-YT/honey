package com.lbee.generator.service;

public interface GeneratorService {

    /**
     * 生成器
     *
     * @param project     项目名
     * @param moduleName  模块名
     * @param packageName 包名
     * @param className   类名
     * @param tableName   数据库表名
     */
    void generator(String project, String moduleName, String packageName, String className, String tableName);
}
