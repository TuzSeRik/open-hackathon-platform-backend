package com.github.itmo.ohp.backend.scoring.module.requests;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ManualScoreRequest {

    ArrayList<ManualScoreDTO> places;
}
