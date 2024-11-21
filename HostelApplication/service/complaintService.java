package com.dtu.HostelApplication.service;
import com.dtu.HostelApplication.entity.user;
import com.dtu.HostelApplication.entity.complaint;
import com.dtu.HostelApplication.repository.complaintRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
@Component
public class complaintService {
    @Autowired
    private complaintRepository complaintRepo;
    @Autowired
    private userService UserService;
    @Transactional
    public void save(complaint Complaint, String userName) {
        Complaint.setComplaintOpenDate(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
        user User= UserService.findbyuserName(userName);
        complaint saved = complaintRepo.save(Complaint);
        User.getComplaints().add(saved);
        UserService.save(User);
    }
    public List<complaint> getAllComplaints() {
        return complaintRepo.findAll();
    }
    public complaint getComplaintById(ObjectId id) {
        return complaintRepo.findById(id).orElse(null);
    }
    @Transactional
    public void deleteComplaintById(ObjectId id, String Id) {
        user User= UserService.findbyId(Id);
        User.getComplaints().removeIf(x -> x.getId().equals(id));
        UserService.save(User);
        complaintRepo.deleteById(id);
    }
    @Transactional
    public void updateComplaint(complaint Complaint) {
        ObjectId id = Complaint.getId();
        boolean newStatus=Complaint.getStatus();
        String updatedDescription=Complaint.getComplaintDetails();
        complaintRepo.updatebyId(id,newStatus,updatedDescription);
    }
}
