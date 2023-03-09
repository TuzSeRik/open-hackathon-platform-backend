package com.github.itmo.ohp.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("invites")
@Data @AllArgsConstructor @NoArgsConstructor
public class Invite {
    @Id
    private Long id;
    @Column("team_id")
    private UUID teamId;
    @Column("is_active")
    private boolean isActive;
}
