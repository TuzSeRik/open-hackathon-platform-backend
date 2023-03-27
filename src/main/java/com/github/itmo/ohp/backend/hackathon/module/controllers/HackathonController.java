package com.github.itmo.ohp.backend.hackathon.module.controllers;

import com.github.itmo.ohp.backend.hackathon.module.models.HackathonModel;
import com.github.itmo.ohp.backend.hackathon.module.requests.CreateHackathonRequest;
import com.github.itmo.ohp.backend.hackathon.module.requests.UpdateHackathonRequest;
import com.github.itmo.ohp.backend.hackathon.module.responses.AllHackathonsResponse;
import com.github.itmo.ohp.backend.hackathon.module.responses.HackathonResponse;
import com.github.itmo.ohp.backend.hackathon.module.services.HackathonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController @RequestMapping("/api/hackathon") @RequiredArgsConstructor
public class HackathonController {
    private final HackathonService hackathonService;
    
    @GetMapping
    public Mono<ResponseEntity<AllHackathonsResponse>> getAllHackathons() {
        return hackathonService.getAllHackathons()
                .map(HackathonResponse::fromHackathonModel).collectList()
                .map(AllHackathonsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<HackathonResponse>> getHackathon(@PathVariable("id") UUID id) {
        return hackathonService.getHackathonById(id)
                .map(HackathonResponse::fromHackathonModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Mono<ResponseEntity<HackathonResponse>> createHackathon(@RequestBody CreateHackathonRequest request) {
        HackathonModel hackathon = HackathonModel.builder()
                .startTime(request.startTime())
                .endTime(request.endTime())
                .isReady(false)
        .build();
        
        return hackathonService.saveHackathon(hackathon)
                .map(HackathonResponse::fromHackathonModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<HackathonResponse>> updateHackathon(@PathVariable("id") UUID id,
                                                                   @RequestBody UpdateHackathonRequest request) {
        HackathonModel hackathon = HackathonModel.builder()
                .startTime(request.startTime())
                .endTime(request.endTime())
                .isReady(request.isReady())
        .build();
        
        return hackathonService.updateHackathon(id, hackathon)
                .map(HackathonResponse::fromHackathonModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<HackathonResponse>> deleteHackathon(@PathVariable("id") UUID id) {
        return hackathonService.deleteHackathon(id)
                .map(HackathonResponse::fromHackathonModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping
    public Mono<ResponseEntity<AllHackathonsResponse>> deleteAllHackathons() {
        return hackathonService.deleteAllHackathons()
                .map(HackathonResponse::fromHackathonModel).collectList()
                .map(AllHackathonsResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}
