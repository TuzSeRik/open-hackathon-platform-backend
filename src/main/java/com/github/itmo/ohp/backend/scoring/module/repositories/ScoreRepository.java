package com.github.itmo.ohp.backend.scoring.module.repositories;

import com.github.itmo.ohp.backend.scoring.module.models.ScoreModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import java.util.UUID;

@Repository
public interface ScoreRepository extends ReactiveCrudRepository<ScoreModel, UUID> {
    Flux<ScoreModel> findAllByTeamId(UUID teamId);
    Flux<Void> deleteAllByTeamId(UUID teamId);
    
}
