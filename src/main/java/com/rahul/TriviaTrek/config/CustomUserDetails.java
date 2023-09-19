package com.rahul.TriviaTrek.config;

import com.rahul.TriviaTrek.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final String username;
    private final String password;
    List<GrantedAuthority> roles;

    public CustomUserDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        roles = new ArrayList<>();
        roles.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "USER";
            }
        });

        if (user.getAdmin()) {
            roles.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "ADMIN";
                }
            });
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
