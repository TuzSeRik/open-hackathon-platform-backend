package com.github.itmo.ohp.backend.scoring.module.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Table("scores") @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class ScoreModel {
    @Id
    private UUID id;
    @NonNull
    private UUID teamId;
    @NonNull
    private Integer score;
    @NonNull
    private String comment;
    
}
