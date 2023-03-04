package com.github.itmo.ohp.backend.requests;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
}
