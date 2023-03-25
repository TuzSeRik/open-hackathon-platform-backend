package com.github.itmo.ohp.backend.hackathon.module.requests;

import java.util.UUID;

public record UpdateTaskProblemRequest(UUID taskId, String type, String problem) {

}
