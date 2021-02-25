package com.lbee.generator.dto;

import com.lbee.generator.entity.TableColumn;
import lombok.Data;

@Data
public class TableColumnDTO extends TableColumn {

    /**
     * 字段类型
     */
    private String attrType;

    /**
     * 实体名称
     */
    private String lowerAttrName;

}
