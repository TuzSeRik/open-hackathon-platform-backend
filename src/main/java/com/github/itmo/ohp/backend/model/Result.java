package com.github.itmo.ohp.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("results")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
    private UUID id;
    private String link;

    //TODO: one to one or many to one --> hackathon
    private UUID hackathonId;
    private UUID userId;

}
