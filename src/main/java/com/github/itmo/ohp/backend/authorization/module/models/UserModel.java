package com.github.itmo.ohp.backend.authorization.module.models;

import com.github.itmo.ohp.backend.configuration.module.SecurityConfiguration;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Table("users") @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class UserModel {
    @Id
    private UUID id;
    @Column("team_id")
    private UUID teamId;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String authorities;
    
    public UserModel setPassword(String password) {
        return setPassword(password, false);
    }
    
    public UserModel setPassword(String password, Boolean shouldEncode) {
        if (shouldEncode) {
            this.password = SecurityConfiguration.passwordEncoder.encode(password);
        }
        else {
            this.password = password;
        }
        
        return this;
    }
    
}
