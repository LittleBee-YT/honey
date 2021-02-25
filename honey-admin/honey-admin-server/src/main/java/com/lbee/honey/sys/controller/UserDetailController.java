package com.lbee.honey.sys.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbee.honey.sys.entity.UserDetail;
import com.lbee.honey.sys.service.UserDetailService;
import com.lbee.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * 系统用户详情扩展
 *
 * @author Little bee
 * @date 2020-11-20 10:38:16
 */
@RestController
@RequestMapping("/userdetail" )
@Api(value = "userdetail", tags = "userdetail管理")
public class UserDetailController {

    @Autowired
    private UserDetailService userDetailService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param userDetail 系统用户详情扩展
     * @return
     */
    @GetMapping("/page")
    public Result getUserDetailPage(Page page, UserDetail userDetail) {
        return Result.success(userDetailService.page(page, Wrappers.query(userDetail)));
    }


    /**
     * 通过id查询系统用户详情扩展
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") String id) {
        return Result.success(userDetailService.getById(id));
    }

    /**
     * 新增系统用户详情扩展
     * @param userDetail 系统用户详情扩展
     * @return Result
     */
    @PostMapping
    public Result save(@RequestBody @Valid UserDetail userDetail) {
        return Result.success(userDetailService.save(userDetail));
    }

    /**
     * 修改系统用户详情扩展
     * @param userDetail 系统用户详情扩展
     * @return Result
     */
    @PutMapping
    public Result updateById(@RequestBody @Valid UserDetail userDetail) {
        return Result.success(userDetailService.updateById(userDetail));
    }

    /**
     * 通过id删除系统用户详情扩展
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable String id) {
        return Result.success(userDetailService.removeById(id));
    }

    /**
     * 通过id集合批量删除系统用户详情扩展
     * @param ids
     * @return Result
     */
    @DeleteMapping("/batch")
    public Result batchRemove(@RequestBody List<String> ids) {
        return Result.success(userDetailService.removeByIds(ids));
    }

}
