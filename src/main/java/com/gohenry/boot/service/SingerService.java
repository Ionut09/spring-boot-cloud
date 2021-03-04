package com.gohenry.boot.service;

import com.gohenry.boot.domain.entity.AlbumEntity;
import com.gohenry.boot.domain.entity.SingerEntity;
import com.gohenry.boot.domain.model.EventType;
import com.gohenry.boot.domain.model.Singer;
import com.gohenry.boot.domain.model.SingerEvent;
import com.gohenry.boot.mapper.AlbumMapper;
import com.gohenry.boot.mapper.SingerMapper;
import com.gohenry.boot.repository.AlbumRepository;
import com.gohenry.boot.repository.SingerRepository;
import com.gohenry.boot.repository.SingerRepositoryJDBC;
import com.gohenry.boot.stream.Channels;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Validated
@EnableBinding(Channels.class)
public class SingerService {

    private final SingerRepositoryJDBC repositoryJDBC;

    private final SingerRepository repository;

    private final AlbumRepository albumRepository;

    private final SingerMapper singerMapper;

    private final AlbumMapper albumMapper;

    private final Channels channels;

    public Singer create(@Valid Singer singer) {
        return repositoryJDBC.create(singer);
    }

    @Transactional(propagation = Propagation.REQUIRED) //REQUIRED is by default
    public Singer createBySpringDataJpa(@Valid Singer singer) {
        SingerEntity singerEntity = singerMapper.toEntity(singer);
//        AlbumEntity albumEntity = albumMapper.toEntity(singer.getAlbums().get(0));
//
//        AlbumEntity savedAlbumEntity = albumRepository.save(albumEntity);
        SingerEntity savedEntity = repository.save(singerEntity);

        SingerEvent payload = SingerEvent.builder()
                                         .singerId(singerEntity.getId())
                                         .created(LocalDateTime.now())
                                         .type(EventType.CREATE)
                                         .build();

        channels.singerOutputChannel()
                .send(MessageBuilder.withPayload(payload)
                                    .setHeader("x-timestamp", System.currentTimeMillis())
                                    .build());

        return singerMapper.toDto(savedEntity);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Singer> getAllSingers() {
        return repository.findAllFetchedEntirely()
                         .stream()
                         .map(singerMapper::toDto)
                         .collect(Collectors.toList());
    }

    public List<Singer> getAllSingersWithoutAlbums() {
//        System.out.println(repository.findIdAndFirstName());
        return repository.findAll()
                         .stream()
                         .map(singerMapper::toDtoWithoutAlbums)
                         .collect(Collectors.toList());
    }

    public Singer update(@Valid Singer singer) {
        //don't call @transactional methods from the same class, they won't transactional
//        updateBySpringDataJpa(singer);
        return repositoryJDBC.update(singer);
    }

    //    @Transactional //has an associated PersistenceContext which will be flushed at commit time of the transaction
    public Singer updateBySpringDataJpa(Singer singer) {
        var id = singer.getId();

        var singerEntity = repository.findById(id)
                                     .orElseThrow(() -> new RuntimeException("I couldn't find the singer enity for id: " + id));

//        if (optionalSingerEntity.isEmpty()){
//            throw new RuntimeException("I couldn't find the singer enity for id: " + id);
//        }
        singerEntity.setFirstName(singer.getFirstName());
        singerEntity.setLastName(singer.getLastName());

        singerEntity = repository.save(singerEntity);
        return singerMapper.toDto(singerEntity);
    }

    /*
        when using @Transactional
        the call to updateBySpringDataJpa is proxied and some aspects are applied before and after the real call
        proxy.updateBySpringDataJpa();

        inside the proxied method which is overriding the real method
            beginTransaction();
            call the real updateBySpringDataJpa() method
            commitTransation();
     */
}
