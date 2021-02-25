package com.lbee.common.data.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 多租户基础实体类
 *
 * @param <T>
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "多租户字段")
public class TenantEntity<T> extends BaseEntity<T> {

    @ApiModelProperty("租户ID")
    private Long tenantId;

}
