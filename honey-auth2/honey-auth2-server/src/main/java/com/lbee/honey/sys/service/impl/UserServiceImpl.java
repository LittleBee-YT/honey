package com.lbee.honey.sys.service.impl;

import com.lbee.common.data.base.BaseDBServiceImpl;
import com.lbee.honey.sys.entity.User;
import com.lbee.honey.sys.mapper.UserMapper;
import com.lbee.honey.sys.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 系统用户
 *
 * @author Little bee
 * @date 2020-11-20 12:23:04
 */
@Service
public class UserServiceImpl extends BaseDBServiceImpl<UserMapper, User>
        implements UserService {

}

