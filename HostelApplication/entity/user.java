package com.dtu.HostelApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexOptions;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class user {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String userName;
    private String password;
    private String hostelId;
     @DBRef
    private List<complaint> Complaints=new ArrayList<>();
     private List<String> Roles=new ArrayList<>();
}
