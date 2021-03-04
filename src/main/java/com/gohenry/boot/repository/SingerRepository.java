package com.gohenry.boot.repository;

import com.gohenry.boot.domain.MySingerDto;
import com.gohenry.boot.domain.entity.SingerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SingerRepository extends JpaRepository<SingerEntity, Long> {

    //to avoid n+1 queries problem override the fetching mode
    //by using JOIN FETCH on the relations you want them loaded
    @Query("SELECT s " +
            "FROM SingerEntity s " +
            "LEFT JOIN FETCH s.albums") //JPQL
    List<SingerEntity> findAllFetchedEntirely();

    //constructor expression
    @Query("SELECT new com.gohenry.boot.domain.MySingerDto(s.id, s.firstName) " +
            "FROM SingerEntity s ")
    List<MySingerDto> findIdAndFirstName();

    List<SingerEntity> findByFirstName(String firstName);
}
