package com.github.itmo.ohp.backend.repositories;

import com.github.itmo.ohp.backend.model.ManualScore;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ManualScoreRepository extends ReactiveCrudRepository<ManualScore, Integer> {
}
