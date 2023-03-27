package com.github.itmo.ohp.backend.hackathon.module.responses;

import com.github.itmo.ohp.backend.hackathon.module.models.TaskModel;
import java.util.UUID;

public record TaskResponse(UUID id, UUID stageId, Boolean isReady) {
    public static TaskResponse fromTaskModel(TaskModel task) {
        return new TaskResponse(
                task.getId(),
                task.getStageId(),
                task.getIsReady()
        );
    }
    
}
