package com.dtu.HostelApplication.repository;

import com.dtu.HostelApplication.entity.complaint;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Component
public interface complaintRepository extends MongoRepository<complaint, ObjectId> {
    public default void updatebyId(ObjectId id, boolean status, String updatedDescription){
    }
}
