package com.future.restoapp.model.security;

import com.future.restoapp.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private final User user;

    public UserPrincipal(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if(user != null){
            if(this.user.getIsAdmin()){
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
            }
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return user != null? this.user.getUsername() : null;
    }

    @Override
    public String getPassword() {
        return user != null? this.user.getPassword() : null;
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
    public boolean isEnabled() { return true; }

    public boolean isAdmin() {
        return user != null? this.user.getIsAdmin() : false;
    }

    public User getUser() { return this.user; }

}
