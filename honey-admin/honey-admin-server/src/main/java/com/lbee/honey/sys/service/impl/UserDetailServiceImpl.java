package com.lbee.honey.sys.service.impl;

import com.lbee.common.data.base.BaseDBServiceImpl;
import com.lbee.honey.sys.entity.UserDetail;
import com.lbee.honey.sys.mapper.UserDetailMapper;
import com.lbee.honey.sys.service.UserDetailService;
import org.springframework.stereotype.Service;

/**
 * 系统用户详情扩展
 *
 * @author Little bee
 * @date 2020-11-20 10:38:16
 */
@Service
public class UserDetailServiceImpl extends BaseDBServiceImpl<UserDetailMapper, UserDetail>
        implements UserDetailService {

}

