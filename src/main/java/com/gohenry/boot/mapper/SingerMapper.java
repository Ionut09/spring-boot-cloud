package com.gohenry.boot.mapper;

import com.gohenry.boot.domain.entity.AlbumEntity;
import com.gohenry.boot.domain.entity.SingerEntity;
import com.gohenry.boot.domain.model.Album;
import com.gohenry.boot.domain.model.Singer;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;

import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class SingerMapper {

    private final AlbumMapper albumMapper;

    public Singer toDto(SingerEntity singerEntity) {
        return Singer.builder()
                     .id(singerEntity.getId())
                     .firstName(singerEntity.getFirstName())
                     .lastName(singerEntity.getLastName())
                     .birthDate(singerEntity.getBirthDate())
                     .albums(mapAlbums(singerEntity.getAlbums()))
                     .build();
    }

    public Singer toDtoWithoutAlbums(SingerEntity singerEntity) {
        singerEntity.setAlbums(Collections.emptyList());
        return toDto(singerEntity);
    }

    public SingerEntity toEntity(Singer singer) {
        return SingerEntity.builder()
                           .id(singer.getId())
                           .firstName(singer.getFirstName())
                           .lastName(singer.getLastName())
                           .birthDate(singer.getBirthDate())
                           .build();
    }

    private List<Album> mapAlbums(List<AlbumEntity> albums) {
        return albums == null ? null : albums.stream()
                                             .map(albumMapper::toDto)
                                             .collect(toList());
    }
}
