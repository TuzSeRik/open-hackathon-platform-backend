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
        return inviteRepository
                .findById(id)
                .flatMap(invite -> inviteRepository.deleteById(invite.getId()).thenReturn(invite));
    }
    
    public Flux<InviteModel> deleteAllInvites() {
        return inviteRepository
                .findAll()
                .collectList()
                .flatMap((invites) -> inviteRepository.deleteAll().thenReturn(invites))
                .flatMapMany(Flux::fromIterable);
    }
    
    public Flux<InviteModel> deleteAllInvitesForTeam(UUID teamId) {
        return inviteRepository
                .findAllByTeamId(teamId)
                .collectList()
                .flatMap((invites) -> inviteRepository.deleteAllByTeamId(teamId).thenReturn(invites))
                .flatMapMany(Flux::fromIterable);
    }
    
}
