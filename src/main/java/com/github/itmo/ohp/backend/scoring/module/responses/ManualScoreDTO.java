package com.github.itmo.ohp.backend.scoring.module.responses;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data @Builder public class ManualScoreDTO {
    Integer id;
    Integer place;
    UUID teamId;
    String comment;
}
