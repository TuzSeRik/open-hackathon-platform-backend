package com.github.itmo.ohp.backend.scoring.module.controllers;

import com.github.itmo.ohp.backend.scoring.module.requests.CreateScoreRequest;
import com.github.itmo.ohp.backend.scoring.module.requests.UpdateScoreRequest;
import com.github.itmo.ohp.backend.scoring.module.responses.AllScoresResponse;
import com.github.itmo.ohp.backend.scoring.module.responses.ScoreResponse;
import com.github.itmo.ohp.backend.scoring.module.services.ScoreService;
import com.github.itmo.ohp.backend.scoring.module.models.ScoreModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController @RequestMapping("/api/score") @RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;
    
    @GetMapping
    public Mono<ResponseEntity<AllScoresResponse>> getAllScores() {
        return scoreService.getAllScores()
                .map(ScoreResponse::fromScoreModel).collectList()
                .map(AllScoresResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/team/{teamId}")
    public Mono<ResponseEntity<AllScoresResponse>> getAllScoresForTeam(@PathVariable("teamId") UUID teamId) {
        return scoreService.getAllScoresForTeam(teamId)
                .map(ScoreResponse::fromScoreModel).collectList()
                .map(AllScoresResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ScoreResponse>> getScore(@PathVariable("id") UUID id) {
        return scoreService.getScoreById(id)
                .map(ScoreResponse::fromScoreModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Mono<ResponseEntity<ScoreResponse>> createScore(@RequestBody CreateScoreRequest request) {
        ScoreModel score = ScoreModel.builder()
                .teamId(request.teamId())
                .score(request.score())
                .comment(request.comment())
        .build();
        
        return scoreService.saveScore(score)
                .map(ScoreResponse::fromScoreModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ScoreResponse>> updateScore(@PathVariable("id") UUID id,
                                                           @RequestBody UpdateScoreRequest request) {
        ScoreModel score = ScoreModel.builder()
                .teamId(request.teamId())
                .score(request.score())
                .comment(request.comment())
        .build();
        
        return scoreService.updateScore(id, score)
                .map(ScoreResponse::fromScoreModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ScoreResponse>> deleteScore(@PathVariable("id") UUID id) {
        return scoreService.deleteScore(id)
                .map(ScoreResponse::fromScoreModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping
    public Mono<ResponseEntity<AllScoresResponse>> deleteAllScores() {
        return scoreService.deleteAllScores()
                .map(ScoreResponse::fromScoreModel).collectList()
                .map(AllScoresResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/team/{teamId}")
    public Mono<ResponseEntity<AllScoresResponse>> deleteAllScoresForTeam(@PathVariable("teamId") UUID teamId) {
        return scoreService.deleteAllScoresForTeam(teamId)
                .map(ScoreResponse::fromScoreModel).collectList()
                .map(AllScoresResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}
