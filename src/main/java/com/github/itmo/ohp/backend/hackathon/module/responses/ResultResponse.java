package com.github.itmo.ohp.backend.hackathon.module.responses;

import com.github.itmo.ohp.backend.hackathon.module.models.ResultModel;
import java.util.UUID;

public record ResultResponse(UUID id, UUID taskProblemId, UUID teamId, String answer, Boolean isAccepted) {
    public static ResultResponse fromResultModel(ResultModel result) {
        return new ResultResponse(
                result.getId(),
                result.getTaskProblemId(),
                result.getTeamId(),
                result.getAnswer(),
                result.getIsAccepted()
        );
    }
    
}
