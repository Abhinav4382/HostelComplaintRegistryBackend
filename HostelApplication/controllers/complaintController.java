package com.dtu.HostelApplication.controllers;

import com.dtu.HostelApplication.entity.complaint;
import com.dtu.HostelApplication.entity.user;
import com.dtu.HostelApplication.repository.complaintRepositoryImpl;
import com.dtu.HostelApplication.service.userService;
import com.dtu.HostelApplication.service.complaintService;
import com.dtu.HostelApplication.utilis.JwtUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/complaint")
public class complaintController {

    @Autowired
    private complaintService ComplaintService;

    @Autowired
    private userService UserService;

    @Autowired
    private complaintRepositoryImpl ComplaintRepositoryImpl;

    @Autowired
    private JwtUtil jwtUtil;

    // Endpoint to add a complaint
    @PostMapping()
    public ResponseEntity<?> addComplaint(@RequestBody complaint Complaint, @RequestHeader("Authorization") String token) {
        try {
            // Extract username from JWT token
            String userName = jwtUtil.extractUsername(token.replace("Bearer ", "").trim());
            ComplaintService.save(Complaint, userName);
            return new ResponseEntity<>(Complaint, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to get a complaint by ID
    @GetMapping("/id/{ComplaintId}")
    public ResponseEntity<?> getComplaintbyId(@PathVariable ObjectId ComplaintId) {
        // Get currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        // Find user and filter complaints by ID
        user User = UserService.findbyuserName(userName);
        List<complaint> collect = User.getComplaints().stream().filter(x -> x.getId().equals(ComplaintId)).toList();

        if (!collect.isEmpty()) {
            Optional<complaint> Complaints = Optional.ofNullable(ComplaintService.getComplaintById(ComplaintId));
            if (Complaints.isPresent()) {
                return new ResponseEntity<>(Complaints.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to get complaints for a user based on JWT
    @GetMapping()
    public ResponseEntity<?> getComplaintsbyjwt(@RequestHeader("Authorization") String token) {
        try {
            // Extract username from JWT token
            String userName = jwtUtil.extractUsername(token.replace("Bearer ", "").trim());
            user User = UserService.findbyuserName(userName);

            // Fetch complaints for the user
            List<complaint> Complaints = ComplaintRepositoryImpl.getUser(User.getHostelId());
            if (Complaints != null && !Complaints.isEmpty()) {
                return new ResponseEntity<>(Complaints, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to update a complaint
    @PutMapping()
    public ResponseEntity<?> updateComplaint(@RequestBody complaint Complaint) {
        try {
            ComplaintService.updateComplaint(Complaint);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
