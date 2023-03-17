package com.github.itmo.ohp.backend.services;

import com.github.itmo.ohp.backend.model.ManualScore;
import com.github.itmo.ohp.backend.repositories.ManualScoreRepository;
import com.github.itmo.ohp.backend.requests.ManualScoreRequest;
import com.github.itmo.ohp.backend.requests.ManualScoreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class ManualScoreService {
    private final ManualScoreRepository repository;

    public Flux<ManualScore> getScores() {
        return repository.findAll();
    }

    public Flux<ManualScore> setScores(ManualScoreRequest req) {
        var places = req.getPlaces();

        if(places == null) return Flux.error(new IllegalArgumentException("Places is null"));

        if(!validateScores(places)) return Flux.error(new IllegalArgumentException("Invalid place entries"));

        var entities = places.stream()
                .map(ManualScoreService::toEntity).toList();

        return clearScores().thenMany(repository.saveAll(entities));
    }

    public Mono<Void> clearScores() {
        return repository.deleteAll();
    }

    private boolean validateScores(ArrayList<ManualScoreDTO> scores) {
        scores.sort(Comparator.comparingInt(ManualScoreDTO::getId));

        //Places must start with 1
        if (scores.size() > 0 && scores.get(0).getPlace() != 1) return false;

        //There can't be gaps in places sequence, e.g. 1, 2, 4 is invalid
        for (int i = 1; i < scores.size(); i++) {
            if(scores.get(i).getPlace() > scores.get(i-1).getPlace() + 1) return false;
        }

        return true;
    }

    //TODO: Create mapper class
    public static ManualScoreDTO toDTO(ManualScore entity) {
        return ManualScoreDTO.builder()
                .id(entity.getId())
                .place(entity.getPlace())
                .teamId(entity.getTeamId())
                .comment(entity.getComment())
                .build();
    }

    private static ManualScore toEntity(ManualScoreDTO dto) {
        return ManualScore.builder()
                .place(dto.getPlace())
                .teamId(dto.getTeamId())
                .comment(dto.getComment())
                .build();
    }
}
