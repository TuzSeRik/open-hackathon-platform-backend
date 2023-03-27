package com.github.itmo.ohp.backend.timeline.module.repositories;

import com.github.itmo.ohp.backend.team.module.model.TeamModel;
import com.github.itmo.ohp.backend.timeline.module.model.TimelineStage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface TimelineStageRepository extends ReactiveCrudRepository<TimelineStage, UUID> {
}
