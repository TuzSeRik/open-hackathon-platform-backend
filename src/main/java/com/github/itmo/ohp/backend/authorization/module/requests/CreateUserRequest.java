package com.github.itmo.ohp.backend.authorization.module.requests;

import java.util.UUID;

public record CreateUserRequest(UUID teamId, String username, String password, String authorities) {

}
