package com.home.funny.model;

import com.home.funny.constant.Authority;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 用户信息
 */
@Data
@Table("home_funny_user_details")
public class HomeFunnyUserDetails implements UserDetails {
    @Id
    private Long id;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<Authority> authorities;

}
