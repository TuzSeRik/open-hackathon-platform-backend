package com.github.itmo.ohp.backend.requests.results;
import lombok.Data;

import java.util.UUID;

@Data
public class MessageRequest {
    private UUID fromId;
    private String message;
    private UUID toId;
}
