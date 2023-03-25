package com.github.itmo.ohp.backend.hackathon.module.repositories;

import com.github.itmo.ohp.backend.hackathon.module.models.ResultModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface ResultRepository extends ReactiveCrudRepository<ResultModel, UUID> {
    Flux<ResultModel> findAllByTaskProblemId(UUID taskProblemId);
    Flux<Void> deleteAllByTaskProblemId(UUID taskProblemId);
    Flux<ResultModel> findAllByTeamId(UUID teamId);
    Flux<Void> deleteAllByTeamId(UUID teamId);
    
}
