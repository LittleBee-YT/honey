package com.lbee.common.core.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbee.common.result.Result;
import com.lbee.common.core.entity.OperationErrorLog;
import com.lbee.common.core.service.OperationErrorLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志错误记录表
 *
 * @author Little bee
 * @date 2020-11-27 16:18:11
 */
@RestController
@RequestMapping("/operationerrorlog" )
@Api(value = "operationerrorlog", tags = "operationerrorlog管理")
public class OperationErrorLogController {

    @Autowired
    private OperationErrorLogService operationErrorLogService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param operationErrorLog 操作日志错误记录表
     * @return
     */
    @GetMapping("/page")
    public Result getOperationErrorLogPage(Page page, OperationErrorLog operationErrorLog) {
        return Result.success(operationErrorLogService.page(page, Wrappers.query(operationErrorLog)));
    }


    /**
     * 通过id查询操作日志错误记录表
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") String id) {
        return Result.success(operationErrorLogService.getById(id));
    }

    /**
     * 通过id删除操作日志错误记录表
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable String id) {
        return Result.success(operationErrorLogService.removeById(id));
    }

    /**
     * 通过id集合批量删除操作日志错误记录表
     * @param ids
     * @return Result
     */
    @DeleteMapping("/batch")
    public Result batchRemove(@RequestBody List<String> ids) {
        return Result.success(operationErrorLogService.removeByIds(ids));
    }

}
