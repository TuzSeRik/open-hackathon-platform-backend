package com.github.itmo.ohp.backend.hackathon.module.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResultRequest {
    private String link;

    private UUID userId;
}
