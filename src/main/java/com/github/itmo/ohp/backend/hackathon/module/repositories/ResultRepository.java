package com.github.itmo.ohp.backend.hackathon.module.repositories;

import com.github.itmo.ohp.backend.hackathon.module.models.ResultModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ResultRepository extends ReactiveCrudRepository<ResultModel, UUID> {
    Flux<ResultModel> findAllByTaskProblemId(UUID taskProblemId);
    Mono<Void> deleteAllByTaskProblemId(UUID taskProblemId);
    Flux<ResultModel> findAllByTeamId(UUID teamId);
    Mono<Void> deleteAllByTeamId(UUID teamId);
    
}
