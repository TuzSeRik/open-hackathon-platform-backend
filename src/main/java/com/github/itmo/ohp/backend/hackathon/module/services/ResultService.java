package com.github.itmo.ohp.backend.hackathon.module.services;

import com.github.itmo.ohp.backend.hackathon.module.models.ResultModel;
import com.github.itmo.ohp.backend.hackathon.module.repositories.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    
    public Flux<ResultModel> getAllResults() {
        return resultRepository.findAll();
    }
    
    public Flux<ResultModel> getAllResultsForTaskProblem(UUID taskProblemId) {
        return resultRepository.findAllByTaskProblemId(taskProblemId);
    }
    
    public Flux<ResultModel> getAllResultsForTeam(UUID teamId) {
        return resultRepository.findAllByTeamId(teamId);
    }
    
    public Mono<ResultModel> getResultById(UUID id) {
        return resultRepository.findById(id);
    }
    
    public Mono<ResultModel> saveResult(ResultModel result) {
        return resultRepository.save(result);
    }
    
    public Mono<ResultModel> updateResult(UUID id, ResultModel result) {
        return resultRepository.findById(id)
                .flatMap(changingResult -> {
                    changingResult.setTaskProblemId(result.getTaskProblemId());
                    changingResult.setTeamId(result.getTeamId());
                    changingResult.setAnswer(result.getAnswer());
                    changingResult.setIsAccepted(result.getIsAccepted());
                    return resultRepository.save(changingResult);
                })
        .switchIfEmpty(Mono.empty());
    }
    
    public Mono<ResultModel> deleteResult(UUID id) {
        Mono<ResultModel> result = resultRepository.findById(id);
        return resultRepository.deleteById(id).then(result);
    }
    
    public Flux<ResultModel> deleteAllResults() {
        Flux<ResultModel> results = resultRepository.findAll();
        return resultRepository.deleteAll().thenMany(results);
    }
    
    public Flux<ResultModel> deleteAllResultsForTaskProblem(UUID taskProblemId) {
        Flux<ResultModel> results = resultRepository.findAllByTaskProblemId(taskProblemId);
        return resultRepository.deleteAllByTaskProblemId(taskProblemId).thenMany(results);
    }
    
    public Flux<ResultModel> deleteAllResultsForTeam(UUID teamId) {
        Flux<ResultModel> results = resultRepository.findAllByTeamId(teamId);
        return resultRepository.deleteAllByTeamId(teamId).thenMany(results);
    }
    
}
