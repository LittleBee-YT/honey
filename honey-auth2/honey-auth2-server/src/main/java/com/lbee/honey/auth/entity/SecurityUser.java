package com.lbee.honey.auth.entity;

import lombok.Data;
import com.lbee.honey.sys.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class SecurityUser implements UserDetails {

    private static final long serialVersionUID = -829210348748820486L;

    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 是否冻结
     */
    private Boolean locked;
    /**
     * 是否禁用
     */
    private Boolean enabled;
    /**
     * 租户Id
     */
    private Long tenantId;
    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser() {

    }

    public SecurityUser(User user, Collection<SimpleGrantedAuthority> authorities) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEnabled(user.getEnabled() == 0);
        this.setLocked(user.getLocked() == 0);
        this.setTenantId(user.getTenantId());
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
