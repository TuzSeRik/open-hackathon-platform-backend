package com.github.itmo.ohp.backend.hackathon.module.repositories;

import com.github.itmo.ohp.backend.hackathon.module.models.TaskModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<TaskModel, UUID> {
    Flux<TaskModel> findAllByStageId(UUID stageId);
    Mono<Void> deleteAllByStageId(UUID stageId);
    
}
