package com.dtu.HostelApplication.controllers;

import com.dtu.HostelApplication.dto.LoginRequest;
import com.dtu.HostelApplication.entity.LoginResponse;
import com.dtu.HostelApplication.entity.user;
import com.dtu.HostelApplication.service.UserDetailsServiceImpl;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth")
public class authController {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public authController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
            );

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());

            // Create response
            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
