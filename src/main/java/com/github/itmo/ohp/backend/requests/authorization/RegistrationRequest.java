package com.github.itmo.ohp.backend.requests.authorization;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
}
