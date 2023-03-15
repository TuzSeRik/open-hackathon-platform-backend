package com.github.itmo.ohp.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Table("info_pages") @Data @AllArgsConstructor @NoArgsConstructor
public class InfoPage {
    @Id
    private UUID id;
    private String text;
    @Column("is_public")
    private Boolean isPublic;
}
