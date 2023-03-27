package com.github.itmo.ohp.backend.authorization.module.controllers;

import com.github.itmo.ohp.backend.authorization.module.models.TeamModel;
import com.github.itmo.ohp.backend.authorization.module.requests.CreateTeamRequest;
import com.github.itmo.ohp.backend.authorization.module.requests.UpdateTeamRequest;
import com.github.itmo.ohp.backend.authorization.module.responses.AllTeamsResponse;
import com.github.itmo.ohp.backend.authorization.module.responses.TeamResponse;
import com.github.itmo.ohp.backend.authorization.module.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController @RequestMapping("/api/team") @RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    
    @GetMapping
    public Mono<ResponseEntity<AllTeamsResponse>> getAllTeams() {
        return teamService.getAllTeams()
                .map(TeamResponse::fromTeamModel).collectList()
                .map(AllTeamsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<TeamResponse>> getTeam(@PathVariable("id") UUID id) {
        return teamService.getTeamById(id)
                .map(TeamResponse::fromTeamModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Mono<ResponseEntity<TeamResponse>> createTeam(@RequestBody CreateTeamRequest request) {
        TeamModel team = TeamModel.builder()
                .name(request.name())
                .github(request.github())
                .info(request.info())
        .build();
        
        return teamService.saveTeam(team)
                .map(TeamResponse::fromTeamModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<TeamResponse>> updateTeam(@PathVariable("id") UUID id,
                                                         @RequestBody UpdateTeamRequest request) {
        TeamModel team = TeamModel.builder()
                .name(request.name())
                .github(request.github())
                .info(request.info())
        .build();
        
        return teamService.updateTeam(id, team)
                .map(TeamResponse::fromTeamModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<TeamResponse>> deleteTeam(@PathVariable("id") UUID id) {
        return teamService.deleteTeam(id)
                .map(TeamResponse::fromTeamModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping
    public Mono<ResponseEntity<AllTeamsResponse>> deleteAllTeams() {
        return teamService.deleteAllTeams()
                .map(TeamResponse::fromTeamModel).collectList()
                .map(AllTeamsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}
