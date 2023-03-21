package com.github.itmo.ohp.backend.information.module;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service @RequiredArgsConstructor
public class InformationService {
    private final InfoPageRepository infoPageRepository;
    
    public Mono<InfoPageModel> updatePublicPage(String text) {
        return getPublicPage()
                .flatMap(p -> {
                    p.setText(text);
                    return infoPageRepository.save(p);
                }
        );
    }
    
    public Mono<InfoPageModel> updatePrivatePage(String text) {
        return getPrivatePage()
                .flatMap(p -> {
                            p.setText(text);
                            return infoPageRepository.save(p);
                        }
                );
    }

    public Mono<InfoPageModel> getPublicPage() {
        return getPageByVisibility(true);
    }
    
    public Mono<InfoPageModel> getPrivatePage() {
        return getPageByVisibility(false);
    }
    
    public Mono<InfoPageModel> getPageByVisibility(Boolean isPublic) {
        return infoPageRepository.getInfoPageByIsPublic(isPublic);
    }
    
}
