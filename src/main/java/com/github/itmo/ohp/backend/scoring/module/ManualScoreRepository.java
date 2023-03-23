package com.github.itmo.ohp.backend.scoring.module;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ManualScoreRepository extends ReactiveCrudRepository<ManualScoreModel, Integer> {
}
