package com.github.itmo.ohp.backend.scoring.module.services;

import com.github.itmo.ohp.backend.scoring.module.ManualScoreModel;
import com.github.itmo.ohp.backend.scoring.module.requests.ManualScoreDTO;
import com.github.itmo.ohp.backend.scoring.module.ManualScoreRepository;
import com.github.itmo.ohp.backend.scoring.module.requests.ManualScoreRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class ManualScoringService {
    private final ManualScoreRepository repository;

    public Flux<ManualScoreModel> getScores() {
        return repository.findAll();
    }

    public Flux<ManualScoreModel> setScores(ManualScoreRequest req) {
        var places = req.getPlaces();

        if(places == null) return Flux.error(new IllegalArgumentException("Places is null"));

        if(!validateScores(places)) return Flux.error(new IllegalArgumentException("Invalid place entries"));

        var entities = places.stream()
                .map(ManualScoringService::toEntity).toList();

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
    public static ManualScoreDTO toDTO(ManualScoreModel entity) {
        return ManualScoreDTO.builder()
                .id(entity.getId())
                .place(entity.getPlace())
                .teamId(entity.getTeamId())
                .comment(entity.getComment())
                .build();
    }

    private static ManualScoreModel toEntity(ManualScoreDTO dto) {
        return ManualScoreModel.builder()
                .place(dto.getPlace())
                .teamId(dto.getTeamId())
                .comment(dto.getComment())
                .build();
    }
}
