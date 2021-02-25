package com.lbee.honey.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbee.honey.sys.entity.Role;
import com.lbee.honey.sys.service.RoleService;
import com.lbee.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 系统用户角色
 *
 * @author Little bee
 * @date 2020-11-19 12:33:03
 */
@RestController
@RequestMapping("/role")
@Api(value = "role", tags = "role管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @param role 系统用户角色
     * @return
     */
    @GetMapping("/page")
    public Result getRolePage(Page page, Role role) {
        System.out.println("-----------------");
        System.out.println(JSONObject.toJSONString(roleService.page(page, Wrappers.query(role))));
        System.out.println("-----------------");
        return Result.success(roleService.page(page, Wrappers.query(role)));
    }


    /**
     * 通过id查询系统用户角色
     *
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") String id) {
        return Result.success(roleService.getById(id));
    }

    /**
     * 新增系统用户角色
     *
     * @param role 系统用户角色
     * @return Result
     */
    @PostMapping
    public Result save(@RequestBody @Valid Role role) {
        return Result.success(roleService.save(role));
    }

    /**
     * 修改系统用户角色
     *
     * @param role 系统用户角色
     * @return Result
     */
    @PutMapping
    public Result updateById(@RequestBody @Valid Role role) {
        return Result.success(roleService.updateById(role));
    }

    /**
     * 通过id删除系统用户角色
     *
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable String id) {
        return Result.success(roleService.removeById(id));
    }

    /**
     * 通过id集合批量删除系统用户角色
     *
     * @param ids
     * @return Result
     */
    @DeleteMapping("/batch")
    public Result batchRemove(@RequestBody List<String> ids) {
        return Result.success(roleService.removeByIds(ids));
    }

}
