package com.github.itmo.ohp.backend.scoring.module.requests;

import java.util.UUID;

public record UpdateScoreRequest(UUID teamId, Integer score, String comment) {

}
