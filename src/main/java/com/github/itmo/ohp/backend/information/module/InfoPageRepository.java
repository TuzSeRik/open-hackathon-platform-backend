package com.github.itmo.ohp.backend.information.module;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface InfoPageRepository extends ReactiveCrudRepository<InfoPageModel, UUID> {
    Mono<InfoPageModel> getInfoPageByIsPublic(Boolean isPublic);
    
}
