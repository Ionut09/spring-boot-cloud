package com.gohenry.boot.repository;

import com.gohenry.boot.domain.entity.AlbumEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {

}
