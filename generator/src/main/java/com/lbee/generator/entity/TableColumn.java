package com.lbee.generator.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TableColumn implements Serializable {

    /**
     * 字段名称
     */
    protected String columnName;

    /**
     * 字段备注
     */
    protected String comments;

    /**
     * 字段类型
     */
    protected String jdbcType;

    /**
     * 数据类型
     */
    protected String dataType;
}
