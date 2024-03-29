package com.github.itmo.ohp.backend.hackathon.module.repositories;

import com.github.itmo.ohp.backend.hackathon.module.models.TaskProblemModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface TaskProblemRepository extends ReactiveCrudRepository<TaskProblemModel, UUID> {
    Flux<TaskProblemModel> findAllByTaskId(UUID taskId);
    Mono<Void> deleteAllByTaskId(UUID taskId);
    
}
