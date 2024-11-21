package com.dtu.HostelApplication.repository;

import com.dtu.HostelApplication.entity.user;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface userRepository extends MongoRepository<user, String> {
    public user findByuserName(String username);
    public  void deleteByuserName(String username);
    }
