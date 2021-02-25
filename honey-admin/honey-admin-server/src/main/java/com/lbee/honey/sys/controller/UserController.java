package com.lbee.honey.sys.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbee.common.core.log.OpLog;
import com.lbee.honey.sys.entity.User;
import com.lbee.honey.sys.service.UserService;
import com.lbee.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * 系统用户
 *
 * @author Little bee
 * @date 2020-11-17 18:05:33
 */
@RestController
@RequestMapping("/user" )
@Api(value = "user", tags = "user管理")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param user 系统用户
     * @return
     */
    @GetMapping("/page")
    @OpLog(module = "系统用户分页查询", type = "query", desc = "系统用户分页查询")
    public Result getUserPage(Page page, User user) {
        int i = 0;
        if (i!=1){
            throw new RuntimeException("2132323");
        }
        return Result.success(userService.page(page, Wrappers.query(user)));
    }


    /**
     * 通过id查询系统用户
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") String id) {
        return Result.success(userService.getById(id));
    }

    /**
     * 新增系统用户
     * @param user 系统用户
     * @return Result
     */
    @PostMapping
    public Result save(@RequestBody @Valid User user) {
        return Result.success(userService.save(user));
    }

    /**
     * 修改系统用户
     * @param user 系统用户
     * @return Result
     */
    @PutMapping
    public Result updateById(@RequestBody @Valid User user) {
        return Result.success(userService.updateById(user));
    }

    /**
     * 通过id删除系统用户
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable String id) {
        return Result.success(userService.removeById(id));
    }

    /**
     * 通过id集合批量删除系统用户
     * @param ids
     * @return Result
     */
    @DeleteMapping("/batch")
    public Result batchRemove(@RequestBody List<String> ids) {
        return Result.success(userService.removeByIds(ids));
    }

}
