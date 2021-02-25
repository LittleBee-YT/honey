package com.lbee.honey.sys.service;

import com.lbee.common.data.base.BaseDBService;
import com.lbee.honey.sys.entity.Role;

import java.util.List;

/**
 * 系统用户角色
 *
 * @author Little bee
 * @date 2020-11-20 12:23:05
 */
public interface RoleService extends BaseDBService<Role> {

    List<Role> selectRoleByUserId(long userId);
}

