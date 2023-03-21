package com.github.itmo.ohp.backend.information.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor
public class InformationResponse {
    private UUID id;
    private String data;
    
}
