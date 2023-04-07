package com.github.itmo.ohp.backend.hackathon.module.services;

import com.github.itmo.ohp.backend.hackathon.module.models.StageModel;
import com.github.itmo.ohp.backend.hackathon.module.repositories.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class StageService {
    private final StageRepository stageRepository;
    
    public Flux<StageModel> getAllStages() {
        return stageRepository.findAll();
    }
    
    public Mono<StageModel> getStageById(UUID id) {
        return stageRepository.findById(id);
    }
    
    public Mono<StageModel> saveStage(StageModel stage) {
        stage.ensureTimeContinuity();
        
        return stageRepository.save(stage);
    }
    
    public Mono<StageModel> updateStage(UUID id, StageModel stage) {
        stage.ensureTimeContinuity();
        
        return stageRepository.findById(id)
                .flatMap(changingStage -> {
                    changingStage.setStartTime(stage.getStartTime());
                    changingStage.setEndTime(stage.getEndTime());
                    changingStage.setIsReady(stage.getIsReady());
                    return stageRepository.save(changingStage);
                })
        .switchIfEmpty(Mono.empty());
    }
    
    public Mono<StageModel> deleteStage(UUID id) {
        return stageRepository
                .findById(id)
                .flatMap(stage -> stageRepository.deleteById(stage.getId()).thenReturn(stage));
    }
    
    public Flux<StageModel> deleteAllStages() {
        return stageRepository
                .findAll()
                .collectList()
                .flatMap((stages) -> stageRepository.deleteAll().thenReturn(stages))
                .flatMapMany(Flux::fromIterable);
    }
    
}
