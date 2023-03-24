package com.github.itmo.ohp.backend.hackathon.module.services;

import com.github.itmo.ohp.backend.hackathon.module.models.HackathonModel;
import com.github.itmo.ohp.backend.hackathon.module.repositories.HackathonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class HackathonService {
    private final HackathonRepository hackathonRepository;
    
    public Flux<HackathonModel> getAllHackathons() {
        return hackathonRepository.findAll();
    }
    
    public Mono<HackathonModel> getHackathonById(UUID id) {
        return hackathonRepository.findById(id);
    }
    
    public Mono<HackathonModel> saveHackathon(HackathonModel hackathon) {
        return hackathonRepository.save(hackathon);
    }
    
    public Mono<HackathonModel> updateHackathon(UUID id, HackathonModel hackathon) {
        return hackathonRepository.findById(id)
                .flatMap(changingHackathon -> {
                    changingHackathon.setStartTime(hackathon.getStartTime());
                    changingHackathon.setEndTime(hackathon.getEndTime());
                    changingHackathon.setIsReady(hackathon.getIsReady());
                    return hackathonRepository.save(changingHackathon);
                })
        .switchIfEmpty(Mono.empty());
    }
    
    public Mono<HackathonModel> deleteHackathon(UUID id) {
        Mono<HackathonModel> hackathon = hackathonRepository.findById(id);
        hackathonRepository.deleteById(id);
        return hackathon;
    }
    
    public Flux<HackathonModel> deleteAllHackathons() {
        Flux<HackathonModel> hackathons = hackathonRepository.findAll();
        hackathonRepository.deleteAll();
        return hackathons;
    }
    
}
