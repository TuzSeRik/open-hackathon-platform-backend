package com.github.itmo.ohp.backend.model;

import com.github.itmo.ohp.backend.responses.authorization.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.util.Set;
import java.util.UUID;

@Table("teams")
@Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class Team {
    @Id
    private UUID id;
    private String name;
    private String github;
    private String info;
    private Set<UserResponse> users;
}
