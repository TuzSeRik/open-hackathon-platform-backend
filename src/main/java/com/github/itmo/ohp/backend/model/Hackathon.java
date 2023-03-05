package com.github.itmo.ohp.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("hackathons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hackathon {
    @Id
    private UUID id;
    private String name;
    private Date startTime;
    private Date endTime;
    private String description;

}
