package com.yenmai.clinicrestfulapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yenmai.clinicrestfulapi.entity.TaiKhoan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author YenMai
 */
public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;


    private String username;

    private String role;

    private String name;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(String  username,
                         String name, String role, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.username = username;
        this.role = role;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple build(TaiKhoan user) {
        List<GrantedAuthority> authorities = user.getAuthorities();

        return new UserPrinciple(
                user.getTenTaiKhoan(),
                user.getNhanVien().getTenNhanVien(),
                user.getQuyen(),
                user.getMatKhau(),
                authorities
        );
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(username, user.username);
    }
}