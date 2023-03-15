package com.github.itmo.ohp.backend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("manual_scores") @Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ManualScore {
    @Id
    private Integer id;
    private Integer place;
    private UUID teamId;
    private String comment;
}
