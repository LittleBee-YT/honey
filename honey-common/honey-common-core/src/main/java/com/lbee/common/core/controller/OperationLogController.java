package com.lbee.common.core.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbee.common.result.Result;
import com.lbee.common.core.entity.OperationLog;
import com.lbee.common.core.service.OperationLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志记录表
 *
 * @author Little bee
 * @date 2020-11-27 16:18:11
 */
@RestController
@RequestMapping("/operationlog" )
@Api(value = "operationlog", tags = "operationlog管理")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param operationLog 操作日志记录表
     * @return
     */
    @GetMapping("/page")
    public Result getOperationLogPage(Page page, OperationLog operationLog) {
        return Result.success(operationLogService.page(page, Wrappers.query(operationLog)));
    }


    /**
     * 通过id查询操作日志记录表
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") String id) {
        return Result.success(operationLogService.getById(id));
    }

    /**
     * 通过id删除操作日志记录表
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable String id) {
        return Result.success(operationLogService.removeById(id));
    }

    /**
     * 通过id集合批量删除操作日志记录表
     * @param ids
     * @return Result
     */
    @DeleteMapping("/batch")
    public Result batchRemove(@RequestBody List<String> ids) {
        return Result.success(operationLogService.removeByIds(ids));
    }

}
