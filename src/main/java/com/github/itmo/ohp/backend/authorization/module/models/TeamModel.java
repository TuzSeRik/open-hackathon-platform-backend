package com.github.itmo.ohp.backend.authorization.module.models;

import com.github.itmo.ohp.backend.authorization.module.responses.UserResponse;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.util.Set;
import java.util.UUID;

@Table("teams") @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class TeamModel {
    @Id
    private UUID id;
    @NonNull
    private String name;
    @NonNull
    private String github;
    @NonNull
    private String info;
    
}
