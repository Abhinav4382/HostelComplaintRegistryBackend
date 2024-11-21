package com.dtu.HostelApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "complaints")
public class complaint {
    @Id
    private ObjectId id;
    @NonNull
    private String complaintType;
    @NonNull
    private String hostelId;
    private LocalDateTime complaintOpenDate;
    private LocalDateTime Date;
    private boolean complaintStatus=true;
    private String complaintDetails;
    public boolean getStatus(){
        return complaintStatus;
    }
}
