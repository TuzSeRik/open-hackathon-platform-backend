package com.github.itmo.ohp.backend.hackathon.module.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("task_problems") @Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class TaskProblemModel {
    @Id
    private UUID id;
    @Column("task_id") @NonNull
    private UUID taskId;
    @NonNull
    private String type;
    @NonNull
    private String problem;
    
}
