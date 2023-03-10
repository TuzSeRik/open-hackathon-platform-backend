package com.github.itmo.ohp.backend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class AuthResponse {
    private Boolean isAuthorized;
}
