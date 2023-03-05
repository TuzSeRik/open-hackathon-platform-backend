package com.github.itmo.ohp.backend.requests.results;

import lombok.Data;

import java.util.Date;
@Data
public class HackathonPhaseTimerRequest {
    private String phaseName;
    private Date endDate;

}
