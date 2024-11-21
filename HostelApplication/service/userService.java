package com.dtu.HostelApplication.service;

import com.dtu.HostelApplication.entity.user;
import com.dtu.HostelApplication.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Component
public class userService {
    @Autowired
    private userRepository UserRepo;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public  user findbyuserName(String username) {
        return UserRepo.findByuserName(username);
    }
    public user findbyId(String Id){
        Optional<user> User= UserRepo.findById(Id);
        return User.orElse(null);
    }
    public void save(user User) {
        User.setPassword(passwordEncoder.encode(User.getPassword()));
        UserRepo.save(User);
    }
    public void saveNewuser(user User) {
        User.setPassword(passwordEncoder.encode(User.getPassword()));
        User.setRoles(Arrays.asList("USER"));
        UserRepo.save(User);
    }
    public List<user> findAll() {
        return UserRepo.findAll();
    }
    public user update(user User) {
        return UserRepo.save(User);
    }
}
