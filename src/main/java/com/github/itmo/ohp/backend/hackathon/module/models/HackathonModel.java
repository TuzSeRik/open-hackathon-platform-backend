package com.github.itmo.ohp.backend.hackathon.module.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Column("start_time")
    private ZonedDateTime startTime;
    @Column("end_time")
    private ZonedDateTime endTime;
    @Column("is_ready")
    private Boolean isReady;
    
}
