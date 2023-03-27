package com.github.itmo.ohp.backend.hackathon.module.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Table("tasks") @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class TaskModel {
    @Id
    private UUID id;
    @Column("stage_id") @NonNull
    private UUID stageId;
    @Column("is_ready") @NonNull
    private Boolean isReady;
    
}
