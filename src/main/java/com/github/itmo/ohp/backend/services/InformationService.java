package com.github.itmo.ohp.backend.services;

import com.github.itmo.ohp.backend.model.InfoPage;
import com.github.itmo.ohp.backend.repositories.InfoPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service @RequiredArgsConstructor
public class InformationService {
    private final InfoPageRepository infoPageRepository;
    
    public Mono<InfoPage> updatePublicPage(String text) {
        return getPublicPage()
                .flatMap(p -> {
                    p.setText(text);
                    return infoPageRepository.save(p);
                }
        );
    }
    
    public Mono<InfoPage> updatePrivatePage(String text) {
        return getPrivatePage()
                .flatMap(p -> {
                            p.setText(text);
                            return infoPageRepository.save(p);
                        }
                );
    }

    public Mono<InfoPage> getPublicPage() {
        return getPageByVisibility(true);
    }
    
    public Mono<InfoPage> getPrivatePage() {
        return getPageByVisibility(false);
    }
    
    public Mono<InfoPage> getPageByVisibility(Boolean isPublic) {
        return infoPageRepository.getInfoPageByIsPublic(isPublic);
    }
}
