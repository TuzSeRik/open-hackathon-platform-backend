package com.github.itmo.ohp.backend.information.module.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Table("information") @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class InformationModel {
    @Id
    private UUID id;
    @NonNull
    private String data;
    @Column("is_public") @NonNull
    private Boolean isPublic;
    
}
