package com.lbee.honey.sys.service.impl;

import com.lbee.common.data.base.BaseDBServiceImpl;
import com.lbee.honey.sys.entity.Role;
import com.lbee.honey.sys.mapper.RoleMapper;
import com.lbee.honey.sys.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 系统用户角色
 *
 * @author Little bee
 * @date 2020-11-19 12:33:03
 */
@Service
public class RoleServiceImpl extends BaseDBServiceImpl<RoleMapper, Role>
        implements RoleService {

}

