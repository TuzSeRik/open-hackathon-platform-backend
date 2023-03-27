package com.github.itmo.ohp.backend.hackathon.module.requests;

import java.util.UUID;

public record CreateResultRequest(UUID taskProblemId, UUID teamId, String answer) {

}
