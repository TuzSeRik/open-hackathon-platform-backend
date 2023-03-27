package com.github.itmo.ohp.backend.hackathon.module.responses;

import com.github.itmo.ohp.backend.hackathon.module.models.HackathonModel;
import java.time.ZonedDateTime;
import java.util.UUID;

public record HackathonResponse(UUID id, ZonedDateTime startTime, ZonedDateTime endTime, Boolean isReady) {
    public static HackathonResponse fromHackathonModel(HackathonModel hackathon) {
        return new HackathonResponse(
                hackathon.getId(),
                hackathon.getStartTime(),
                hackathon.getEndTime(),
                hackathon.getIsReady()
        );
    }
    
}
