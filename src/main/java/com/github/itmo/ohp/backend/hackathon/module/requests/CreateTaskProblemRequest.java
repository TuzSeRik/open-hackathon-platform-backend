package com.github.itmo.ohp.backend.hackathon.module.requests;

import java.util.UUID;

public record CreateTaskProblemRequest(UUID taskId, String type, String problem) {

}
