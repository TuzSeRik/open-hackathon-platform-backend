package com.github.itmo.ohp.backend.scoring.module;

import com.github.itmo.ohp.backend.scoring.module.requests.ManualScoreDTO;
import com.github.itmo.ohp.backend.scoring.module.requests.ManualScoreRequest;
import com.github.itmo.ohp.backend.scoring.module.services.ManualScoringService;
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
    private final ManualScoringService service;

    @GetMapping
    @ResponseBody
    public Flux<ManualScoreDTO> getScores() {
        return service.getScores().map(ManualScoringService::toDTO);
    }

    //TODO: Use global exception handling
    //TODO: Authorization
    @PutMapping
    @ResponseBody
    public Flux<ManualScoreDTO> setScores(@RequestBody ManualScoreRequest request) {
        return service.setScores(request).map(ManualScoringService::toDTO)
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
