package com.github.itmo.ohp.backend.authorization.module.requests;

import java.util.UUID;

public record UpdateInviteRequest(UUID teamId, Boolean isActive) {

}
