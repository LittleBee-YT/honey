package com.lbee.common.core.service.impl;

import com.lbee.common.data.base.BaseDBServiceImpl;
import com.lbee.common.core.entity.OperationLog;
import com.lbee.common.core.mapper.OperationLogMapper;
import com.lbee.common.core.service.OperationLogService;
import org.springframework.stereotype.Service;

/**
 * 操作日志记录表
 *
 * @author Little bee
 * @date 2020-11-27 16:18:11
 */
@Service
public class OperationLogServiceImpl extends BaseDBServiceImpl<OperationLogMapper, OperationLog>
        implements OperationLogService {

}

