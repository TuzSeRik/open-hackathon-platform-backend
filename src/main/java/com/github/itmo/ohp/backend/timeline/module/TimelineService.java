package com.github.itmo.ohp.backend.timeline.module;


import com.github.itmo.ohp.backend.timeline.module.model.TimelineStage;
import com.github.itmo.ohp.backend.timeline.module.repositories.TimelineStageRepository;
import com.github.itmo.ohp.backend.timeline.module.requests.TimelineStageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TimelineService {

    private final TimelineStageRepository stageRepository;

    public Flux<TimelineStage> getAllStages(){
        return stageRepository.findAllByOrderByStartTimestampAsc();
    }

    public Mono<TimelineStage> getStage(UUID id){
        return stageRepository.findById(id);
    }

    public Mono<TimelineStage> createStage(TimelineStageRequest request){
        TimelineStage stage = request.toTimelineStage();
        stage.setId(UUID.randomUUID());
        return stageRepository.save(stage);
    }

    public Mono<TimelineStage> updateStage(TimelineStageRequest request, UUID id){
        TimelineStage stage = request.toTimelineStage();
        stage.setId(id);
        return stageRepository.save(stage);
    }

    public Mono<Void> deleteStage(UUID id){
        return stageRepository.deleteById(id);
    }

    public Mono<TimelineStage> getCurrentStage(){
        LocalDateTime currentTime = LocalDateTime.now();
        return getAllStages().filter(obj -> {
                    LocalDateTime start = obj.getStartTimestamp();
                    LocalDateTime end = obj.getEndTimestamp();
                    return currentTime.isEqual(start) || currentTime.isEqual(end) ||
                            (currentTime.isAfter(start) && currentTime.isBefore(end));
                }).next();
    }
}
