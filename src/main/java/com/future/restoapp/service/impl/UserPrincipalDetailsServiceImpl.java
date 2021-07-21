package com.future.restoapp.service.impl;

import com.future.restoapp.domain.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.future.restoapp.domain.User;
import com.future.restoapp.repository.UserRepository;

@Service
public class UserPrincipalDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserPrincipalDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(s);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }

}
