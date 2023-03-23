package com.github.itmo.ohp.backend.authorization.module;

import com.github.itmo.ohp.backend.configuration.module.SecurityConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Table("users") @Data @AllArgsConstructor @NoArgsConstructor
public class UserModel {
    @Id
    private UUID id;
    private String username;
    private String password;
    private String authorities;
    @Column("team_id")
    private UUID teamId;
    
    public UserModel setPassword(String password) {
        this.password = SecurityConfiguration.passwordEncoder.encode(password);
        
        return this;
    }
    
}
