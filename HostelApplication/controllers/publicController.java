package com.dtu.HostelApplication.controllers;

import com.dtu.HostelApplication.entity.user;
import com.dtu.HostelApplication.service.UserDetailsServiceImpl;
import com.dtu.HostelApplication.service.userService;
import com.dtu.HostelApplication.utilis.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/public")
public class  publicController {
    @Autowired
    private userService UserService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "OK";
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody user User) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        user UserInDb = UserService.findbyuserName(userName);
        UserInDb.setUserName(User.getUserName());
        UserInDb.setPassword(User.getPassword());
        UserService.save(UserInDb);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
