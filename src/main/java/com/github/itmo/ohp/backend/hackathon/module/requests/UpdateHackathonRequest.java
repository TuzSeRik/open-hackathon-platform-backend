package com.github.itmo.ohp.backend.hackathon.module.requests;

import java.time.ZonedDateTime;

public record UpdateHackathonRequest (ZonedDateTime startTime, ZonedDateTime endTime, Boolean isReady) {

}
