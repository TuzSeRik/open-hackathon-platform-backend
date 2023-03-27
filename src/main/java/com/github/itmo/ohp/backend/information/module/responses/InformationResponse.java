package com.github.itmo.ohp.backend.information.module.responses;

import com.github.itmo.ohp.backend.information.module.models.InformationModel;
import java.util.UUID;

public record InformationResponse(UUID id, String data, Boolean isPublic) {
    public static InformationResponse fromInformationModel(InformationModel information) {
        return new InformationResponse(
                information.getId(),
                information.getData(),
                information.getIsPublic()
        );
    }
    
}
