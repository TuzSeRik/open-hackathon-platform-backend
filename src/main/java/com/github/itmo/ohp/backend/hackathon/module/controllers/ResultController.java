package com.github.itmo.ohp.backend.hackathon.module.controllers;

import com.github.itmo.ohp.backend.hackathon.module.models.ResultModel;
import com.github.itmo.ohp.backend.hackathon.module.requests.CreateResultRequest;
import com.github.itmo.ohp.backend.hackathon.module.requests.UpdateResultRequest;
import com.github.itmo.ohp.backend.hackathon.module.responses.AllResultsResponse;
import com.github.itmo.ohp.backend.hackathon.module.responses.ResultResponse;
import com.github.itmo.ohp.backend.hackathon.module.services.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController @RequestMapping("/api/result") @RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;
    
    @GetMapping
    public Mono<ResponseEntity<AllResultsResponse>> getAllResults() {
        return resultService.getAllResults()
                .map(ResultResponse::fromResultModel).collectList()
                .map(AllResultsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/task-problem/{taskProblemId}")
    public Mono<ResponseEntity<AllResultsResponse>> getAllResultsForTaskProblem(@PathVariable("taskProblemId") UUID taskProblemId) {
        return resultService.getAllResultsForTaskProblem(taskProblemId)
                .map(ResultResponse::fromResultModel).collectList()
                .map(AllResultsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/team/{teamId}")
    public Mono<ResponseEntity<AllResultsResponse>> getAllResultsForTeam(@PathVariable("teamId") UUID teamId) {
        return resultService.getAllResultsForTeam(teamId)
                .map(ResultResponse::fromResultModel).collectList()
                .map(AllResultsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ResultResponse>> getResult(@PathVariable("id") UUID id) {
        return resultService.getResultById(id)
                .map(ResultResponse::fromResultModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Mono<ResponseEntity<ResultResponse>> createResult(@RequestBody CreateResultRequest request) {
        ResultModel result = ResultModel.builder()
                .taskProblemId(request.taskProblemId())
                .teamId(request.teamId())
                .answer(request.answer())
                .isAccepted(false)
        .build();
        
        return resultService.saveResult(result)
                .map(ResultResponse::fromResultModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ResultResponse>> updateResult(@PathVariable("id") UUID id,
                                                             @RequestBody UpdateResultRequest request) {
        ResultModel result = ResultModel.builder()
                .taskProblemId(request.taskProblemId())
                .teamId(request.teamId())
                .answer(request.answer())
                .isAccepted(request.isAccepted())
        .build();
        
        return resultService.updateResult(id, result)
                .map(ResultResponse::fromResultModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ResultResponse>> deleteResult(@PathVariable("id") UUID id) {
        return resultService.deleteResult(id)
                .map(ResultResponse::fromResultModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping
    public Mono<ResponseEntity<AllResultsResponse>> deleteAllResults() {
        return resultService.deleteAllResults()
                .map(ResultResponse::fromResultModel).collectList()
                .map(AllResultsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/task-problem/{taskProblemId}")
    public Mono<ResponseEntity<AllResultsResponse>> deleteAllResultsForTask(@PathVariable("taskProblemId") UUID taskProblemId) {
        return resultService.deleteAllResultsForTaskProblem(taskProblemId)
                .map(ResultResponse::fromResultModel).collectList()
                .map(AllResultsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/team/{teamId}")
    public Mono<ResponseEntity<AllResultsResponse>> deleteAllResultsForTeam(@PathVariable("teamId") UUID teamId) {
        return resultService.deleteAllResultsForTeam(teamId)
                .map(ResultResponse::fromResultModel).collectList()
                .map(AllResultsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}
