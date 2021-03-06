package com.lbee.generator.service;

import com.lbee.generator.entity.TableColumn;

import java.util.List;

public interface TableColumnService {

    /**
     * 获取数据表字段
     *
     * @param tableName
     * @return
     */
    List<TableColumn> getTableColumnList(String tableName);

    /**
     * 查询表备注信息
     *
     * @param tableName
     * @return
     */
    String queryTableInfo(String tableName);
}
