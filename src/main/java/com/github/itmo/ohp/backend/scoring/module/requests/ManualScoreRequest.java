package com.github.itmo.ohp.backend.scoring.module.requests;

import com.github.itmo.ohp.backend.scoring.module.responses.ManualScoreDTO;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ManualScoreRequest {

    ArrayList<ManualScoreDTO> places;
}
