package com.gohenry.boot.mapper;

import com.gohenry.boot.domain.entity.AlbumEntity;
import com.gohenry.boot.domain.model.Album;

import org.springframework.stereotype.Component;

@Component
public class AlbumMapper {

    public AlbumEntity toEntity(Album album) {
        return AlbumEntity.builder()
                          .description(album.getDescription())
                          .name(album.getName())
                          .releaseDate(album.getReleaseDate())
                          .build();
    }

    public Album toDto(AlbumEntity album) {
        return Album.builder()
                    .id(album.getId())
                    .description(album.getDescription())
                    .name(album.getName())
                    .releaseDate(album.getReleaseDate())
                    .build();
    }
}
