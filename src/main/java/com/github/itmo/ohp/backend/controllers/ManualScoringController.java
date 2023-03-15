package com.github.itmo.ohp.backend.controllers;

import com.github.itmo.ohp.backend.requests.ManualScoreDTO;
import com.github.itmo.ohp.backend.requests.ManualScoreRequest;
import com.github.itmo.ohp.backend.services.ManualScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class ManualScoringController {
    private final ManualScoreService service;

    @GetMapping
    @ResponseBody
    public Flux<ManualScoreDTO> getScores() {
        return service.getScores().map(ManualScoreService::toDTO);
    }

    //TODO: Use global exception handling
    //TODO: Authorization
    @PutMapping
    @ResponseBody
    public Flux<ManualScoreDTO> setScores(@RequestBody ManualScoreRequest request) {
        return service.setScores(request).map(ManualScoreService::toDTO)
                .onErrorMap(IllegalArgumentException.class,
                        error -> {
                            System.out.println(error.getMessage());
                            return new ResponseStatusException(HttpStatus.BAD_REQUEST);
                        });
    }

    @DeleteMapping
    @ResponseBody
    public Mono<Void> clearScores() {
        return service.clearScores();
    }
}
