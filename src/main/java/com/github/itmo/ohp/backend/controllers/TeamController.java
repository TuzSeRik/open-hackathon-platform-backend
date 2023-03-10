package com.github.itmo.ohp.backend.controllers;

import com.github.itmo.ohp.backend.model.Invite;
import com.github.itmo.ohp.backend.model.Team;
import com.github.itmo.ohp.backend.model.User;
import com.github.itmo.ohp.backend.repositories.InviteRepository;
import com.github.itmo.ohp.backend.repositories.TeamRepository;
import com.github.itmo.ohp.backend.repositories.UserRepository;
import com.github.itmo.ohp.backend.services.AuthorizationService;
import com.github.itmo.ohp.backend.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamRepository teamRepository;
    private final InviteRepository inviteRepository;
    private final UserRepository userRepository;
    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<?> getTeams() {
        Flux<Team> list = teamService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Team>> getTeam(@PathVariable UUID id) {
        Mono<Team> team = teamService.findById(id);
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


    //TODO: add Owner field to Team object and check for ownership
    @PostMapping("/{id}/invite")
    public Mono<ResponseEntity<Invite>> createInvite(@PathVariable UUID id) {
        Invite invite = new Invite(null, id, true);
        Mono<Invite> result = inviteRepository.save(invite);
        return result.map(ResponseEntity::ok)
                     .onErrorReturn(ResponseEntity.badRequest().build())
                     .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }

    @GetMapping("/{id}/invite")
    public Mono<ResponseEntity<Object>> inviteToTeam(Principal principal, @PathVariable UUID id) {
        Mono<Invite> invite = inviteRepository.findByTeamId(id);
        return invite.map(result -> {
            if (result.isActive()){
                userRepository.findByUsername(principal.getName()).subscribe(user -> {
                    user.setTeamId(id);
                    userRepository.save(user);
                    System.out.println("Setting new team for user " + principal.getName() + ": " + id);
                });
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    //Inverses activation on put request
    @PutMapping("/{id}/invite")
    public Mono<ResponseEntity<Mono<Invite>>> updateInvite(@PathVariable UUID id) {
        Mono<Invite> invite = inviteRepository.findByTeamId(id);
        return invite.map(result -> {
            result.setActive(!result.isActive());
            inviteRepository.save(result);
            return ResponseEntity.ok(invite);
        }).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
