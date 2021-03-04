package com.gohenry.boot.domain.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Album {

    private long id;

    private String name;

    private LocalDate releaseDate;

    private String description;
}
