package ${package}.${moduleName}.service.impl;

import ${basePackageName}.data.base.BaseDBServiceImpl;
import ${package}.${moduleName}.entity.${className};
import ${package}.${moduleName}.mapper.${className}Mapper;
import ${package}.${moduleName}.service.${className}Service;
import org.springframework.stereotype.Service;

/**
 * ${comments}
 *
 * @author ${author}
 * @date ${datetime}
 */
@Service
public class ${className}ServiceImpl extends BaseDBServiceImpl<${className}Mapper, ${className}>
        implements ${className}Service {

}
