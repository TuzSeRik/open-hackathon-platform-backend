package com.github.itmo.ohp.backend.authorization.module.services;

import com.github.itmo.ohp.backend.authorization.module.models.InviteModel;
import com.github.itmo.ohp.backend.authorization.module.repositories.InviteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class InviteService {
    private final InviteRepository inviteRepository;
    
    public Flux<InviteModel> getAllInvites() {
        return inviteRepository.findAll();
    }
    
    public Flux<InviteModel> getAllInvitesForTeam(UUID teamId) {
        return inviteRepository.findAllByTeamId(teamId);
    }
    
    public Mono<InviteModel> getInviteById(UUID id) {
        return inviteRepository.findById(id);
    }
    
    public Mono<InviteModel> saveInvite(InviteModel invite) {
        return inviteRepository.save(invite);
    }
    
    public Mono<InviteModel> updateInvite(UUID id, InviteModel invite) {
        return inviteRepository.findById(id)
                .flatMap(changingInvite -> {
                    changingInvite.setTeamId(invite.getTeamId());
                    changingInvite.setIsActive(invite.getIsActive());
                    return inviteRepository.save(changingInvite);
                })
        .switchIfEmpty(Mono.empty());
    }
    
    public Mono<InviteModel> deleteInvite(UUID id) {
        Mono<InviteModel> invite = inviteRepository.findById(id);
        inviteRepository.deleteById(id);
        return invite;
    }
    
    public Flux<InviteModel> deleteAllInvites() {
        Flux<InviteModel> invites = inviteRepository.findAll();
        inviteRepository.deleteAll();
        return invites;
    }
    
    public Flux<InviteModel> deleteAllInvitesForTeam(UUID teamId) {
        Flux<InviteModel> results = inviteRepository.findAllByTeamId(teamId);
        inviteRepository.deleteAllByTeamId(teamId);
        return results;
    }
    
}
