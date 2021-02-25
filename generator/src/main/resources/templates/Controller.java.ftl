package ${package}.${moduleName}.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package}.${moduleName}.entity.${className};
import ${package}.${moduleName}.service.${className}Service;
import ${basePackageName}.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * ${comments}
 *
 * @author ${author}
 * @date ${datetime}
 */
@RestController
@RequestMapping("/${pathName}" )
@Api(value = "${pathName}", tags = "${pathName}管理")
public class ${className}Controller {

    @Autowired
    private ${className}Service ${classname}Service;

    /**
     * 分页查询
     * @param page 分页对象
     * @param ${classname} ${comments}
     * @return
     */
    @GetMapping("/page")
    public Result get${className}Page(Page page, ${className} ${classname}) {
        return Result.success(${classname}Service.page(page, Wrappers.query(${classname})));
    }


    /**
     * 通过id查询${comments}
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") String id) {
        return Result.success(${classname}Service.getById(id));
    }

    /**
     * 新增${comments}
     * @param ${classname} ${comments}
     * @return Result
     */
    @PostMapping
    public Result save(@RequestBody @Valid ${className} ${classname}) {
        return Result.success(${classname}Service.save(${classname}));
    }

    /**
     * 修改${comments}
     * @param ${classname} ${comments}
     * @return Result
     */
    @PutMapping
    public Result updateById(@RequestBody @Valid ${className} ${classname}) {
        return Result.success(${classname}Service.updateById(${classname}));
    }

    /**
     * 通过id删除${comments}
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable String id) {
        return Result.success(${classname}Service.removeById(id));
    }

    /**
     * 通过id集合批量删除${comments}
     * @param ids
     * @return Result
     */
    @DeleteMapping("/batch")
    public Result batchRemove(@RequestBody List<String> ids) {
        return Result.success(${classname}Service.removeByIds(ids));
    }

}