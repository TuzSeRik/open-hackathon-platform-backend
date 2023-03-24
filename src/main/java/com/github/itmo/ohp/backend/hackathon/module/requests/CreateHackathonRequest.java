package com.github.itmo.ohp.backend.hackathon.module.requests;

import java.time.ZonedDateTime;

public record CreateHackathonRequest(ZonedDateTime startTime, ZonedDateTime endTime) {

}
