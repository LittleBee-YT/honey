package com.lbee.honey.sys.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbee.honey.sys.entity.RelationUserRole;
import com.lbee.honey.sys.service.RelationUserRoleService;
import com.lbee.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * 系统用户角色关联
 *
 * @author Little bee
 * @date 2020-11-19 12:33:03
 */
@RestController
@RequestMapping("/relationuserrole" )
@Api(value = "relationuserrole", tags = "relationuserrole管理")
public class RelationUserRoleController {

    @Autowired
    private RelationUserRoleService relationUserRoleService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param relationUserRole 系统用户角色关联
     * @return
     */
    @GetMapping("/page")
    public Result getRelationUserRolePage(Page page, RelationUserRole relationUserRole) {
        return Result.success(relationUserRoleService.page(page, Wrappers.query(relationUserRole)));
    }


    /**
     * 通过id查询系统用户角色关联
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") String id) {
        return Result.success(relationUserRoleService.getById(id));
    }

    /**
     * 新增系统用户角色关联
     * @param relationUserRole 系统用户角色关联
     * @return Result
     */
    @PostMapping
    public Result save(@RequestBody @Valid RelationUserRole relationUserRole) {
        return Result.success(relationUserRoleService.save(relationUserRole));
    }

    /**
     * 修改系统用户角色关联
     * @param relationUserRole 系统用户角色关联
     * @return Result
     */
    @PutMapping
    public Result updateById(@RequestBody @Valid RelationUserRole relationUserRole) {
        return Result.success(relationUserRoleService.updateById(relationUserRole));
    }

    /**
     * 通过id删除系统用户角色关联
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable String id) {
        return Result.success(relationUserRoleService.removeById(id));
    }

    /**
     * 通过id集合批量删除系统用户角色关联
     * @param ids
     * @return Result
     */
    @DeleteMapping("/batch")
    public Result batchRemove(@RequestBody List<String> ids) {
        return Result.success(relationUserRoleService.removeByIds(ids));
    }

}
