package com.github.itmo.ohp.backend.timeline.module.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("timeline_stages")
@Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class TimelineStage {
    @Id
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime startTimestamp;
    private LocalDateTime endTimestamp;
    private StageActionType actionType;
}
