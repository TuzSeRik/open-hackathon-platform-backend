package com.github.itmo.ohp.backend.information.module.controllers;

import com.github.itmo.ohp.backend.information.module.requests.UpdateInformationRequest;
import com.github.itmo.ohp.backend.information.module.responses.InformationResponse;
import com.github.itmo.ohp.backend.information.module.services.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController @RequestMapping("/api/actions/information") @RequiredArgsConstructor
public class InformationModuleController {
    // Place for specific, "short-cutting" actions in the module
    private final InformationService informationService;
    
    @GetMapping("/public")
    public Mono<ResponseEntity<InformationResponse>> getPublicInformation() {
        return informationService.getAllInformationByVisibility(true).next()
                .map(InformationResponse::fromInformationModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/private")
    public Mono<ResponseEntity<InformationResponse>> getPrivateInformation() {
        return informationService.getAllInformationByVisibility(false).next()
                .map(InformationResponse::fromInformationModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/public")
    public Mono<ResponseEntity<InformationResponse>> updatePublicInformation(@RequestBody UpdateInformationRequest request) {
        return informationService.getAllInformationByVisibility(true).next()
                .flatMap(information -> {
                    information.setData(request.data());
                    return informationService.saveInformation(information);
                })
                .map(InformationResponse::fromInformationModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/private")
    public Mono<ResponseEntity<InformationResponse>> setPrivateInfoPage(@RequestBody UpdateInformationRequest request) {
        return informationService.getAllInformationByVisibility(false).next()
                .flatMap(information -> {
                    information.setData(request.data());
                    return informationService.saveInformation(information);
                })
                .map(InformationResponse::fromInformationModel)
                .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}
