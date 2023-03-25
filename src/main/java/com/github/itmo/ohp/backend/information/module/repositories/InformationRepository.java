package com.github.itmo.ohp.backend.information.module.repositories;

import com.github.itmo.ohp.backend.information.module.models.InformationModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import java.util.UUID;

@Repository
public interface InformationRepository extends ReactiveCrudRepository<InformationModel, UUID> {
    Flux<InformationModel> getInformationByIsPublic(Boolean isPublic);
    
}
