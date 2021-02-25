package com.lbee.common.core.service.impl;


import com.lbee.common.core.entity.OperationErrorLog;
import com.lbee.common.core.mapper.OperationErrorLogMapper;
import com.lbee.common.core.service.OperationErrorLogService;
import com.lbee.common.data.base.BaseDBServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 操作日志错误记录表
 *
 * @author Little bee
 * @date 2020-11-27 16:18:11
 */
@Service
public class OperationErrorLogServiceImpl extends BaseDBServiceImpl<OperationErrorLogMapper, OperationErrorLog>
        implements OperationErrorLogService {

}

