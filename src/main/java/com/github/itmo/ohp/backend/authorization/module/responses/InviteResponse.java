package com.github.itmo.ohp.backend.authorization.module.responses;

import com.github.itmo.ohp.backend.authorization.module.models.InviteModel;
import java.util.UUID;

public record InviteResponse(UUID id, UUID teamId, Boolean isActive) {
    public static InviteResponse fromInviteModel(InviteModel invite) {
        return new InviteResponse(
                invite.getId(),
                invite.getTeamId(),
                invite.getIsActive()
        );
    }
    
}
