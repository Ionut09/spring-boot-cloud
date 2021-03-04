package com.gohenry.boot.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SingerEvent {

    private EventType type;

    private long singerId;

    private LocalDateTime created;
}
