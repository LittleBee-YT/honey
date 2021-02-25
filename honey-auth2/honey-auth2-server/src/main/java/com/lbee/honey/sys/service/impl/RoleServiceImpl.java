package com.lbee.honey.sys.service.impl;

import com.lbee.common.data.base.BaseDBServiceImpl;
import com.lbee.honey.sys.entity.Role;
import com.lbee.honey.sys.mapper.RoleMapper;
import com.lbee.honey.sys.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统用户角色
 *
 * @author Little bee
 * @date 2020-11-20 12:23:05
 */
@Service
public class RoleServiceImpl extends BaseDBServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Override
    public List<Role> selectRoleByUserId(long userId) {
        return new ArrayList<>();
    }
}

