package com.github.itmo.ohp.backend.information.module.controllers;

import com.github.itmo.ohp.backend.information.module.models.InformationModel;
import com.github.itmo.ohp.backend.information.module.requests.CreateInformationRequest;
import com.github.itmo.ohp.backend.information.module.requests.UpdateInformationRequest;
import com.github.itmo.ohp.backend.information.module.responses.AllInformationResponse;
import com.github.itmo.ohp.backend.information.module.responses.InformationResponse;
import com.github.itmo.ohp.backend.information.module.services.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController @RequestMapping("/api/information") @RequiredArgsConstructor
public class InformationController {
    private final InformationService informationService;
    
    @GetMapping
    public Mono<ResponseEntity<AllInformationResponse>> getAllInformation() {
        return informationService.getAllInformation()
                .map(InformationResponse::fromInformationModel).collectList()
                .map(AllInformationResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<InformationResponse>> getInformation(@PathVariable("id") UUID id) {
        return informationService.getInformationById(id)
                .map(InformationResponse::fromInformationModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Mono<ResponseEntity<InformationResponse>> createInformation(@RequestBody CreateInformationRequest request) {
        InformationModel information = InformationModel.builder()
                .data(request.data())
                .isPublic(request.isPublic())
        .build();
        
        return informationService.saveInformation(information)
                .map(InformationResponse::fromInformationModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<InformationResponse>> updateInformation(@PathVariable("id") UUID id,
                                                                       @RequestBody UpdateInformationRequest request) {
        InformationModel information = InformationModel.builder()
                .data(request.data())
                .isPublic(request.isPublic())
        .build();
        
        return informationService.updateInformation(id, information)
                .map(InformationResponse::fromInformationModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<InformationResponse>> deleteInformation(@PathVariable("id") UUID id) {
        return informationService.deleteInformation(id)
                .map(InformationResponse::fromInformationModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping
    public Mono<ResponseEntity<AllInformationResponse>> deleteAllInformation() {
        return informationService.deleteAllInformation()
                .map(InformationResponse::fromInformationModel).collectList()
                .map(AllInformationResponse::new)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}
