package com.github.itmo.ohp.backend.timeline.module.requests;

import com.github.itmo.ohp.backend.timeline.module.model.StageActionType;
import com.github.itmo.ohp.backend.timeline.module.model.TimelineStage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimelineStageRequest {
    private String name;
    private String description;
    private LocalDateTime startTimestamp;
    private LocalDateTime endTimestamp;
    private StageActionType actionType;

    public TimelineStage toTimelineStage(){
        return new TimelineStage(null, name, description, startTimestamp, endTimestamp, actionType);
    }
}
