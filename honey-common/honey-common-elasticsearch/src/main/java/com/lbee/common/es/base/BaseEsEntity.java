package com.lbee.common.es.base;

import com.lbee.common.es.annotations.EsField;
import com.lbee.common.es.constant.EsFieldType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEsEntity implements Serializable {

    @EsField(name = "id")
    private String id;

    @EsField(name = "creat_date", type = EsFieldType.DATE)
    private Date createDate;
}
