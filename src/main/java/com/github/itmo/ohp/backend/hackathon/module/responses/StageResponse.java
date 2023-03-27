package com.github.itmo.ohp.backend.hackathon.module.responses;

import com.github.itmo.ohp.backend.hackathon.module.models.StageModel;
import java.time.ZonedDateTime;
import java.util.UUID;

public record StageResponse(UUID id, UUID hackathonId, ZonedDateTime startTime, ZonedDateTime endTime, Boolean isReady) {
    public static StageResponse fromStageModel(StageModel stage) {
        return new StageResponse(
                stage.getId(),
                stage.getHackathonId(),
                stage.getStartTime(),
                stage.getEndTime(),
                stage.getIsReady()
        );
    }
    
}
