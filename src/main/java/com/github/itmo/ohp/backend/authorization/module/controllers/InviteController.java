package com.github.itmo.ohp.backend.authorization.module.controllers;

import com.github.itmo.ohp.backend.authorization.module.models.InviteModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

public class InviteController {
    //TODO: add Owner field to Team object and check for ownership
//    @PostMapping("/{id}/invite")
//    public Mono<ResponseEntity<InviteModel>> createInvite(@PathVariable UUID id) {
//        InviteModel invite = new InviteModel(null, id, true);
//        Mono<InviteModel> result = inviteRepository.save(invite);
//        return result.map(ResponseEntity::ok)
//                .onErrorReturn(ResponseEntity.badRequest().build())
//                .defaultIfEmpty(ResponseEntity.internalServerError().build());
//    }
//
//    @GetMapping("/{id}/invite")
//    public Mono<ResponseEntity<Object>> inviteToTeam(Principal principal, @PathVariable UUID id) {
//        Mono<InviteModel> invite = inviteRepository.findByTeamId(id);
//        return invite.map(result -> {
//            if (result.isActive()){
//                userRepository.findByUsername(principal.getName()).subscribe(user -> {
//                    user.setTeamId(id);
//                    userRepository.save(user);
//                    System.out.println("Setting new team for user " + principal.getName() + ": " + id);
//                });
//                return ResponseEntity.ok().build();
//            }
//            else {
//                return ResponseEntity.notFound().build();
//            }
//        }).defaultIfEmpty(ResponseEntity.notFound().build());
//    }
//
//    //Inverses activation on put request
//    @PutMapping("/{id}/invite")
//    public Mono<ResponseEntity<Mono<InviteModel>>> updateInvite(@PathVariable UUID id) {
//        Mono<InviteModel> invite = inviteRepository.findByTeamId(id);
//        return invite.map(result -> {
//            result.setActive(!result.isActive());
//            inviteRepository.save(result);
//            return ResponseEntity.ok(invite);
//        }).defaultIfEmpty(ResponseEntity.notFound().build());
//    }
}
