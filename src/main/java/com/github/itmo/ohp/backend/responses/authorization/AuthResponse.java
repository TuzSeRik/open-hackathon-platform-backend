package com.github.itmo.ohp.backend.responses.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class AuthResponse {
    private Boolean isAuthorized;
}
