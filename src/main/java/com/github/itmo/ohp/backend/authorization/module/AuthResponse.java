package com.github.itmo.ohp.backend.authorization.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class AuthResponse {
    private Boolean isAuthorized;
    private List<String> authorities;
    
}