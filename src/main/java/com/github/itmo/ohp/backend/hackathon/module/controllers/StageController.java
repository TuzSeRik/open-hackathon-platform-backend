package com.github.itmo.ohp.backend.hackathon.module.controllers;

import com.github.itmo.ohp.backend.hackathon.module.models.StageModel;
import com.github.itmo.ohp.backend.hackathon.module.requests.CreateStageRequest;
import com.github.itmo.ohp.backend.hackathon.module.requests.UpdateStageRequest;
import com.github.itmo.ohp.backend.hackathon.module.responses.AllStagesResponse;
import com.github.itmo.ohp.backend.hackathon.module.responses.StageResponse;
import com.github.itmo.ohp.backend.hackathon.module.services.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController @RequestMapping("/api/stage") @RequiredArgsConstructor
public class StageController {
    private final StageService stageService;
    
    @GetMapping
    public Mono<ResponseEntity<AllStagesResponse>> getAllStages() {
        return stageService.getAllStages()
                .map(StageResponse::fromStageModel).collectList()
                .map(AllStagesResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<StageResponse>> getStage(@PathVariable("id") UUID id) {
        return stageService.getStageById(id)
                .map(StageResponse::fromStageModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Mono<ResponseEntity<StageResponse>> createStage(@RequestBody CreateStageRequest request) {
        StageModel stage = StageModel.builder()
                .hackathonId(request.hackathonId())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .isReady(false)
        .build();
        
        return stageService.saveStage(stage)
                .map(StageResponse::fromStageModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<StageResponse>> updateStage(@PathVariable("id") UUID id,
                                                           @RequestBody UpdateStageRequest request) {
        StageModel stage = StageModel.builder()
                .hackathonId(request.hackathonId())
                .startTime(request.startTime())
                .endTime(request.endTime())
        .build();
        
        return stageService.updateStage(id, stage)
                .map(StageResponse::fromStageModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<StageResponse>> deleteStage(@PathVariable("id") UUID id) {
        return stageService.deleteStage(id)
                .map(StageResponse::fromStageModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping
    public Mono<ResponseEntity<AllStagesResponse>> deleteAllStages() {
        return stageService.deleteAllStages()
                .map(StageResponse::fromStageModel).collectList()
                .map(AllStagesResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}
