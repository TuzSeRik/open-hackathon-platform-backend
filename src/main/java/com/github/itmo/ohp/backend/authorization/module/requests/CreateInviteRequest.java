package com.github.itmo.ohp.backend.authorization.module.requests;

import java.util.UUID;

public record CreateInviteRequest(UUID teamId, Boolean isActive) {

}
