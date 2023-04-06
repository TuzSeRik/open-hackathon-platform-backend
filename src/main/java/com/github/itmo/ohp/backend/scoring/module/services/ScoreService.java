package com.github.itmo.ohp.backend.scoring.module.services;

import com.github.itmo.ohp.backend.scoring.module.models.ScoreModel;
import com.github.itmo.ohp.backend.scoring.module.repositories.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class ScoreService {
    private final ScoreRepository scoreRepository;
    
    public Flux<ScoreModel> getAllScores() {
        return scoreRepository.findAll();
    }
    
    public Flux<ScoreModel> getAllScoresForTeam(UUID teamId) {
        return scoreRepository.findAllByTeamId(teamId);
    }
    
    public Mono<ScoreModel> getScoreById(UUID id) {
        return scoreRepository.findById(id);
    }
    
    public Mono<ScoreModel> saveScore(ScoreModel score) {
        return scoreRepository.save(score);
    }
    
    public Mono<ScoreModel> updateScore(UUID id, ScoreModel score) {
        return scoreRepository.findById(id)
                .flatMap(changingScore -> {
                    changingScore.setTeamId(score.getTeamId());
                    changingScore.setScore(score.getScore());
                    changingScore.setComment(score.getComment());
                    return scoreRepository.save(changingScore);
                })
        .switchIfEmpty(Mono.empty());
    }
    
    public Mono<ScoreModel> deleteScore(UUID id) {
        return scoreRepository
                .findById(id)
                .flatMap(score -> scoreRepository.deleteById(score.getId()).thenReturn(score));
    }
    
    public Flux<ScoreModel> deleteAllScores() {
        return scoreRepository
                .findAll()
                .collectList()
                .flatMap((scores) -> scoreRepository.deleteAll().thenReturn(scores))
                .flatMapMany(Flux::fromIterable);
    }
    
    public Flux<ScoreModel> deleteAllScoresForTeam(UUID teamId) {
        return scoreRepository
                .findAllByTeamId(teamId)
                .collectList()
                .flatMap((scores) -> scoreRepository.deleteAllByTeamId(teamId).thenReturn(scores))
                .flatMapMany(Flux::fromIterable);
    }
    
}
