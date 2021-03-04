package com.gohenry.boot.domain.entity;

import com.gohenry.boot.domain.model.EventType;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "singerLog")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SingerEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    private EventType type;

    private long singerId;

    private LocalDateTime created;
}
