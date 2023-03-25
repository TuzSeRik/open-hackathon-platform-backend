package com.github.itmo.ohp.backend.information.module.services;

import com.github.itmo.ohp.backend.information.module.models.InformationModel;
import com.github.itmo.ohp.backend.information.module.repositories.InformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class InformationService {
    private final InformationRepository informationRepository;
    
    public Flux<InformationModel> getAllInformation() {
        return informationRepository.findAll();
    }
    
    public Mono<InformationModel> getInformationById(UUID id) {
        return informationRepository.findById(id);
    }
    
    public Flux<InformationModel> getAllInformationByVisibility(Boolean isPublic) {
        return informationRepository.getInformationByIsPublic(isPublic);
    }
    
    public Mono<InformationModel> saveInformation(InformationModel information) {
        return informationRepository.save(information);
    }
    
    public Mono<InformationModel> updateInformation(UUID id, InformationModel information) {
        return informationRepository.findById(id)
                .flatMap(changingInformation -> {
                    changingInformation.setData(information.getData());
                    changingInformation.setIsPublic(information.getIsPublic());
                    return informationRepository.save(changingInformation);
                })
        .switchIfEmpty(Mono.empty());
    }
    
    public Mono<InformationModel> deleteInformation(UUID id) {
        Mono<InformationModel> information = informationRepository.findById(id);
        informationRepository.deleteById(id);
        return information;
    }
    
    public Flux<InformationModel> deleteAllInformation() {
        Flux<InformationModel> information = informationRepository.findAll();
        informationRepository.deleteAll();
        return information;
    }
    
}
