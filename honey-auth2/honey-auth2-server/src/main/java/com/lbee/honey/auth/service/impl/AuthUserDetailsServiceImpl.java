package com.lbee.honey.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lbee.common.exception.BusinessException;
import com.lbee.common.auth.constant.MessageConstant;
import com.lbee.common.result.ResultCode;
import com.lbee.honey.auth.entity.SecurityUser;
import com.lbee.honey.sys.entity.Role;
import com.lbee.honey.sys.entity.User;
import com.lbee.honey.sys.service.RoleService;
import com.lbee.honey.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AuthUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询库里是否存在该用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, username);
        User user = userService.getOne(queryWrapper);
        // 如果user不存在
        if (null == user || StringUtils.isEmpty(user.getPassword())) {
            log.error("Token鉴权失败, User not exist. username:{}", username);
            throw new BusinessException(ResultCode.LOGIN_NAME.getCode(), ResultCode.LOGIN_NAME.getMessage());
        }
        // 密码Encoder
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        SecurityUser securityUser;
        try {
            // 查询用户角色
            List<Role> roleList = roleService.selectRoleByUserId(user.getId());
            // 校验用户状态
            securityUser = new SecurityUser(user, toAuthorities(roleList));
        } catch (Exception e) {
            throw new BusinessException(403, MessageConstant.ROLE_ERROR);
        }
        if (!securityUser.isEnabled()) { // 用户是否禁用
            throw new BusinessException(403, MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new BusinessException(403, MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new BusinessException(403, MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new BusinessException(403, MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }

    private List<SimpleGrantedAuthority> toAuthorities(List<Role> roles) {
        List<SimpleGrantedAuthority> sgaList = new ArrayList<>();
        if (null != roles) {
            roles.stream().forEach(role ->
                    sgaList.add(new SimpleGrantedAuthority(role.getKey()))
            );
        }
        return sgaList;
    }
}
