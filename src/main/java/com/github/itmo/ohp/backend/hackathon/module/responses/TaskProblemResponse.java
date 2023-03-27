package com.github.itmo.ohp.backend.hackathon.module.responses;

import com.github.itmo.ohp.backend.hackathon.module.models.TaskProblemModel;
import java.util.UUID;

public record TaskProblemResponse(UUID id, UUID taskId, String type, String problem) {
    public static TaskProblemResponse fromTaskProblemModel(TaskProblemModel taskProblem) {
        return new TaskProblemResponse(
                taskProblem.getId(),
                taskProblem.getTaskId(),
                taskProblem.getType(),
                taskProblem.getProblem()
        );
    }
    
}
