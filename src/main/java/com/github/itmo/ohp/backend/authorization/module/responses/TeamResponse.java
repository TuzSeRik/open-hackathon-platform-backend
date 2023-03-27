package com.github.itmo.ohp.backend.authorization.module.responses;

import com.github.itmo.ohp.backend.authorization.module.models.TeamModel;
import java.util.UUID;

public record TeamResponse(UUID id, String name, String github, String info) {
    public static TeamResponse fromTeamModel(TeamModel team) {
        return new TeamResponse(
                team.getId(),
                team.getName(),
                team.getGithub(),
                team.getInfo()
        );
    }
    
}
