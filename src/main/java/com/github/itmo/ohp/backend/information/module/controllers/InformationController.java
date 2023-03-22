package com.github.itmo.ohp.backend.information.module.controllers;

import com.github.itmo.ohp.backend.information.module.InformationChangeRequest;
import com.github.itmo.ohp.backend.information.module.InformationResponse;
import com.github.itmo.ohp.backend.information.module.services.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController @RequestMapping("/information") @RequiredArgsConstructor
public class InformationController {
    private final InformationService informationService;
    
    @PostMapping("/public")
    public Mono<ResponseEntity<InformationResponse>> setPublicInfoPage(@RequestBody InformationChangeRequest request) {
        return informationService.updatePublicPage(request.getData())
                .map(p -> new ResponseEntity<>(
                      new InformationResponse()
                              .setId(p.getId())
                              .setData(p.getText()),
                      HttpStatus.OK
                ));
    }
    
    @GetMapping("/public")
    public Mono<ResponseEntity<InformationResponse>> getPublicInfoPage() {
        return informationService.getPublicPage().map(
          p -> new ResponseEntity<>(
                  new InformationResponse()
                      .setId(p.getId())
                      .setData(p.getText()),
                  HttpStatus.OK)
        );
    }
    
    @PostMapping("/private")
    public Mono<ResponseEntity<InformationResponse>> setPrivateInfoPage(@RequestBody InformationChangeRequest request) {
        return informationService.updatePrivatePage(request.getData())
                .map(p -> new ResponseEntity<>(
                        new InformationResponse()
                                .setId(p.getId())
                                .setData(p.getText()),
                        HttpStatus.OK
                ));
    }
    
    @GetMapping("/private")
    public Mono<ResponseEntity<InformationResponse>> getPrivateInfoPage() {
        return informationService.getPrivatePage().map(
                p -> new ResponseEntity<>(
                        new InformationResponse()
                                .setId(p.getId())
                                .setData(p.getText()),
                        HttpStatus.OK)
        );
    }
    
}
