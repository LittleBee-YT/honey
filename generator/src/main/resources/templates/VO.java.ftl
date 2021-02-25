package ${package}.${moduleName}.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import ${basePackageName}.data.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#list columns as column>
    <#if 0 == column.columnName?index_of("is_")>
        import com.baomidou.mybatisplus.annotation.TableField;
        <#break>
    </#if>
</#list>
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* ${comments} VO
*
* @author ${author}
* @date ${datetime}
*/
@Data
@ApiModel(description = "${comments}")
public class ${className}VO extends BaseVO {
private static final long serialVersionUID = 1L;

<#list columns as column>
    <#if column.lowerAttrName != "id" >
        <#if 0 != column.columnName?index_of("is_")>
    @ApiModelProperty(value = "${column.comments}")
    private ${column.attrType} ${column.lowerAttrName};
        <#else>
    @ApiModelProperty(value = "${column.comments}")
    private ${column.attrType} has${column.lowerAttrName?substring(2)};
        </#if>
    </#if>
</#list>

}
