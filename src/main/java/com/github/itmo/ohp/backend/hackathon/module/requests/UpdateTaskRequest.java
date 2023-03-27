package com.github.itmo.ohp.backend.hackathon.module.requests;

import java.util.UUID;

public record UpdateTaskRequest(UUID stageId, Boolean isReady) {

}
