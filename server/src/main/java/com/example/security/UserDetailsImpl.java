package com.example.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Long studentId;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String password,
            boolean admin, Long studentId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.studentId = studentId;
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(admin
                ? "ADMIN"
                : "STUDENT"));
    }

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

}
