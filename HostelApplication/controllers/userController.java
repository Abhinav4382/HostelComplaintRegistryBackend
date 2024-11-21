package com.dtu.HostelApplication.controllers;

import com.dtu.HostelApplication.entity.user;
import com.dtu.HostelApplication.repository.userRepository;
import com.dtu.HostelApplication.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user")
public class userController {

    @Autowired
    private userService UserService;

    @Autowired
    private userRepository UserRepository;

    public userController(AuthenticationConfiguration authenticationConfiguration) {
    }

    // Endpoint to get all users
    @GetMapping()
    public List<user> getAllUsers() {
        return UserService.findAll();
    }

    // Endpoint to update user details
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody user User) {
        // Get currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        // Find user in the database and update details
        user UserInDb = UserService.findbyuserName(userName);
        if (UserInDb != null) {
            UserInDb.setUserName(User.getUserName());
            UserInDb.setPassword(User.getPassword());
            UserService.save(UserInDb);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to delete user
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser() {
        // Get currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        // Delete user by username
        if (UserRepository.existsByuserName(userName)) {
            UserRepository.deleteByuserName(userName);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
