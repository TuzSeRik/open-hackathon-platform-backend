package com.github.itmo.ohp.backend.authorization.module.controllers;

import com.github.itmo.ohp.backend.authorization.module.models.InviteModel;
import com.github.itmo.ohp.backend.authorization.module.requests.CreateInviteRequest;
import com.github.itmo.ohp.backend.authorization.module.requests.UpdateInviteRequest;
import com.github.itmo.ohp.backend.authorization.module.responses.AllInvitesResponse;
import com.github.itmo.ohp.backend.authorization.module.responses.InviteResponse;
import com.github.itmo.ohp.backend.authorization.module.services.InviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController @RequestMapping("/api/invite") @RequiredArgsConstructor
public class InviteController {
    private final InviteService inviteService;
    
    @GetMapping
    public Mono<ResponseEntity<AllInvitesResponse>> getAllInvites() {
        return inviteService.getAllInvites()
                .map(InviteResponse::fromInviteModel).collectList()
                .map(AllInvitesResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/team/{teamId}")
    public Mono<ResponseEntity<AllInvitesResponse>> getAllInvitesForTeam(@PathVariable("teamId") UUID teamId) {
        return inviteService.getAllInvitesForTeam(teamId)
                .map(InviteResponse::fromInviteModel).collectList()
                .map(AllInvitesResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<InviteResponse>> getInvite(@PathVariable("id") UUID id) {
        return inviteService.getInviteById(id)
                .map(InviteResponse::fromInviteModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Mono<ResponseEntity<InviteResponse>> createInvite(@RequestBody CreateInviteRequest request) {
        InviteModel invite = InviteModel.builder()
                .teamId(request.teamId())
                .isActive(request.isActive())
        .build();
        
        return inviteService.saveInvite(invite)
                .map(InviteResponse::fromInviteModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<InviteResponse>> updateInvite(@PathVariable("id") UUID id,
                                                             @RequestBody UpdateInviteRequest request) {
        InviteModel invite = InviteModel.builder()
                .teamId(request.teamId())
                .isActive(request.isActive())
        .build();
        
        return inviteService.updateInvite(id, invite)
                .map(InviteResponse::fromInviteModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<InviteResponse>> deleteInvite(@PathVariable("id") UUID id) {
        return inviteService.deleteInvite(id)
                .map(InviteResponse::fromInviteModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping
    public Mono<ResponseEntity<AllInvitesResponse>> deleteAllInvites() {
        return inviteService.deleteAllInvites()
                .map(InviteResponse::fromInviteModel).collectList()
                .map(AllInvitesResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/team/{teamId}")
    public Mono<ResponseEntity<AllInvitesResponse>> deleteAllInvitesForTeam(@PathVariable("teamId") UUID teamId) {
        return inviteService.deleteAllInvitesForTeam(teamId)
                .map(InviteResponse::fromInviteModel).collectList()
                .map(AllInvitesResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}
