package com.github.itmo.ohp.backend.hackathon.module.requests;

import lombok.Data;

import java.util.Date;
@Data
public class HackathonPhaseTimerRequest {
    private String phaseName;
    private Date endDate;

}
