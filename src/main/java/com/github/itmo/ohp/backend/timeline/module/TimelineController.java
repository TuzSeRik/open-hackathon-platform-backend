package com.github.itmo.ohp.backend.timeline.module;

import com.github.itmo.ohp.backend.timeline.module.model.TimelineStage;
import com.github.itmo.ohp.backend.timeline.module.requests.TimelineStageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController
@RequestMapping("/stages")
@RequiredArgsConstructor
public class TimelineController {

    private final TimelineService timelineService;

    @GetMapping
    public ResponseEntity<?> getStages() {
        Flux<TimelineStage> list = timelineService.getAllStages();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/current")
    public Mono<ResponseEntity<TimelineStage>> getCurrentStage() {
        Mono<TimelineStage> stage = timelineService.getCurrentStage();
        return stage.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<TimelineStage>> getStage(@PathVariable UUID id) {
        Mono<TimelineStage> stage = timelineService.getStage(id);
        return stage.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<TimelineStage>> createStage(@RequestBody TimelineStageRequest stage) {
        Mono<TimelineStage> result = timelineService.createStage(stage);
        return result.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<TimelineStage>> updateStage(@RequestBody TimelineStageRequest stage, @PathVariable UUID id) {
        Mono<TimelineStage> result = timelineService.updateStage(stage, id);
        return result.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteStage(@PathVariable UUID id) {
        Mono<Void> result = timelineService.deleteStage(id);
        return result.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
