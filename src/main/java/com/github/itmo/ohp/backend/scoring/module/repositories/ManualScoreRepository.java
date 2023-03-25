package com.github.itmo.ohp.backend.scoring.module.repositories;

import com.github.itmo.ohp.backend.scoring.module.models.ManualScoreModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ManualScoreRepository extends ReactiveCrudRepository<ManualScoreModel, Integer> {
}
