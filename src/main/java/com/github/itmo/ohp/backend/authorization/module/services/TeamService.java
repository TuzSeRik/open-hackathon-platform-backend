package com.github.itmo.ohp.backend.authorization.module.services;

import com.github.itmo.ohp.backend.authorization.module.models.TeamModel;
import com.github.itmo.ohp.backend.authorization.module.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class TeamService{
    private final TeamRepository teamRepository;
    
    public Flux<TeamModel> getAllTeams() {
        return teamRepository.findAll();
    }
    
    public Mono<TeamModel> getTeamById(UUID id) {
        return teamRepository.findById(id);
    }
    
    public Mono<TeamModel> saveTeam(TeamModel team) {
        return teamRepository.save(team);
    }
    
    public Mono<TeamModel> updateTeam(UUID id, TeamModel team) {
        return teamRepository.findById(id)
                .flatMap(changingTeam -> {
                    changingTeam.setName(team.getName());
                    changingTeam.setGithub(team.getGithub());
                    changingTeam.setInfo(team.getInfo());
                    return teamRepository.save(changingTeam);
                })
        .switchIfEmpty(Mono.empty());
    }
    
    public Mono<TeamModel> deleteTeam(UUID id) {
        Mono<TeamModel> team = teamRepository.findById(id);
        teamRepository.deleteById(id);
        return team;
    }
    
    public Flux<TeamModel> deleteAllTeams() {
        Flux<TeamModel> teams = teamRepository.findAll();
        teamRepository.deleteAll();
        return teams;
    }
    
}
