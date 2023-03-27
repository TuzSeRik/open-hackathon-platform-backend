package com.github.itmo.ohp.backend.scoring.module.responses;

import com.github.itmo.ohp.backend.scoring.module.models.ScoreModel;
import java.util.UUID;

public record ScoreResponse(UUID id, UUID teamId, Integer score, String comment) {
    public static ScoreResponse fromScoreModel(ScoreModel score) {
        return new ScoreResponse(
                score.getId(),
                score.getTeamId(),
                score.getScore(),
                score.getComment()
        );
    }
    
}
