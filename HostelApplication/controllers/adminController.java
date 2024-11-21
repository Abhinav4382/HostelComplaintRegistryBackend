package com.dtu.HostelApplication.controllers;

import com.dtu.HostelApplication.entity.complaint;
import com.dtu.HostelApplication.entity.user;
import com.dtu.HostelApplication.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private userService UserService;

    // Update user details
    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody user User) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        // Find the user in the database
        user UserInDb = UserService.findbyuserName(userName);
        if (UserInDb != null) {
            UserInDb.setUserName(User.getUserName());
            UserInDb.setPassword(User.getPassword());
            UserService.save(UserInDb);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create a new user
    @PostMapping("/new-user")
    public ResponseEntity<?> createUser(@RequestBody user User) {
        try {
            UserService.saveNewuser(User);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get all complaints of the authenticated user
    @GetMapping("/all-complaints")
    public ResponseEntity<?> getAllComplaintsOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        // Find the user in the database
        user UserInDb = UserService.findbyuserName(userName);
        if (UserInDb != null) {
            List<complaint> allComplaints = UserInDb.getComplaints();
            if (allComplaints.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(allComplaints, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
