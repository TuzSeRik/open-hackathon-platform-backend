package com.github.itmo.ohp.backend.hackathon.module.requests;

import java.time.ZonedDateTime;
import java.util.UUID;

public record CreateStageRequest(UUID hackathonId, ZonedDateTime startTime, ZonedDateTime endTime) {

}
