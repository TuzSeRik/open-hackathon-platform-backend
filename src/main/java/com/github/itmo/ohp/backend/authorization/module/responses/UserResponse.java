package com.github.itmo.ohp.backend.authorization.module.responses;

import com.github.itmo.ohp.backend.authorization.module.models.UserModel;
import java.util.UUID;

public record UserResponse(UUID id, UUID teamId, String username, String authorities) {
    public static UserResponse fromUserModel(UserModel user) {
        return new UserResponse(
                user.getId(),
                user.getTeamId(),
                user.getUsername(),
                user.getAuthorities()
        );
    }
    
}
