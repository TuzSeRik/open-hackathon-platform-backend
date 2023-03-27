package com.github.itmo.ohp.backend.hackathon.module.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.ZonedDateTime;
import java.util.UUID;

@Table("hackathons") @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class HackathonModel {
    @Id
    private UUID id;
    @Column("start_time") @NonNull
    private ZonedDateTime startTime;
    @Column("end_time") @NonNull
    private ZonedDateTime endTime;
    @Column("is_ready") @NonNull
    private Boolean isReady;
    
    public Boolean ensureTimeContinuity() {
        if (endTime.isBefore(startTime)) {
            ZonedDateTime swap = startTime;
            startTime = endTime;
            endTime = swap;
            
            return false;
        }
        else {
            return true;
        }
    }
    
}
