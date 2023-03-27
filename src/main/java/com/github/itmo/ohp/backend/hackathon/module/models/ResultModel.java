package com.github.itmo.ohp.backend.hackathon.module.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Table("results") @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class ResultModel {
    @Id
    private UUID id;
    @Column("task_problem_id") @NonNull
    private UUID taskProblemId;
    @Column("team_id") @NonNull
    private UUID teamId;
    @NonNull
    private String answer;
    @Column("is_accepted") @NonNull
    private Boolean isAccepted;
    
}
