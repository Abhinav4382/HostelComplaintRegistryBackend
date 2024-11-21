package com.dtu.HostelApplication.service;

import com.dtu.HostelApplication.entity.user;
import com.dtu.HostelApplication.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private userRepository UserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user User=UserRepository.findByuserName(username);
        if(User!=null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(User.getUserName())
                    .password(User.getPassword())
                    .roles(User.getRoles().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("User not found : " + username);
    }
}
