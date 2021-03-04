package com.gohenry.boot.repository;

import com.gohenry.boot.domain.entity.SingerEventEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<SingerEventEntity, Long> {

}
