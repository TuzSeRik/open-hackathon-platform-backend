package com.github.itmo.ohp.backend.requests.results;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResultRequest {
    private String link;
    private UUID id;
}
