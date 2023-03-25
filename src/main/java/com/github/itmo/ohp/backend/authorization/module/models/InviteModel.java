package com.github.itmo.ohp.backend.authorization.module.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Table("invites") @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class InviteModel {
    @Id
    private UUID id;
    @Column("team_id") @NonNull
    private UUID teamId;
    @Column("is_active") @NonNull
    private Boolean isActive;
    
}
