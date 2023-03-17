package com.github.itmo.ohp.backend.model;

import com.github.itmo.ohp.backend.configuration.SecurityConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Table("users") @Data @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    private UUID id;
    private String username;
    private String password;
    private String authorities;
    @Column("team_id")
    private UUID teamId;
    
    public User setPassword(String password) {
        this.password = SecurityConfiguration.passwordEncoder.encode(password);
        
        return this;
    }
}
