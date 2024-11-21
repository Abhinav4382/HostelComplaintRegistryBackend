package com.dtu.HostelApplication.repository;

import com.dtu.HostelApplication.entity.complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

    public class complaintRepositoryImpl {

        @Autowired
        private MongoTemplate mongoTemplate;
        public List<complaint> getUser(String hostelId) {
            Query query = new Query();
            query.addCriteria(Criteria.where("hostelId").is(hostelId));
            return mongoTemplate.find(query, complaint.class);
        }
    }
