package com.github.itmo.ohp.backend.controllers;

import com.github.itmo.ohp.backend.model.Team;
import com.github.itmo.ohp.backend.repositories.TeamRepository;
import com.github.itmo.ohp.backend.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamRepository teamRepository;

    @GetMapping
    public ResponseEntity<?> getTeams() {
        Flux<Team> list = teamRepository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Team>> getTeam(@PathVariable UUID id) {
        Mono<Team> team = teamRepository.findById(id);
        return team.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
        //return ResponseEntity.ok().body(team);
    }

    @PostMapping
    public Mono<ResponseEntity<Team>> createTeam(@RequestBody Team team) {
        Mono<Team> result = teamRepository.save(team);
        return result.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Team>> updateTeam(@RequestBody Team team) {
        Mono<Team> result = teamRepository.save(team);
        return result.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTeam(@PathVariable UUID id) {
        Mono<Void> result = teamRepository.deleteById(id);
        return result.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
