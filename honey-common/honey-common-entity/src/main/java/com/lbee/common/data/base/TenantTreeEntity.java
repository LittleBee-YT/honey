package com.lbee.common.data.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "多租户字段")
public class TenantTreeEntity<T> extends TreeEntity<T> {

    @ApiModelProperty("租户ID")
    private Long tenantId;

}
