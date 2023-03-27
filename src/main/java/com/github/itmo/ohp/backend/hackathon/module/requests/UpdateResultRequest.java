package com.github.itmo.ohp.backend.hackathon.module.requests;

import java.util.UUID;

public record UpdateResultRequest(UUID taskProblemId, UUID teamId, String answer, Boolean isAccepted) {

}
