package com.github.itmo.ohp.backend.repositories;

import com.github.itmo.ohp.backend.model.InfoPage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface InfoPageRepository extends ReactiveCrudRepository<InfoPage, UUID> {
    Mono<InfoPage> getInfoPageByIsPublic(Boolean isPublic);
}
