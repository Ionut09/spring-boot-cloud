package com.gohenry.boot.controller;

import com.gohenry.boot.domain.model.Singer;
import com.gohenry.boot.service.SingerService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/singers")
@AllArgsConstructor
public class SingerController {

    private final SingerService service;

    @GetMapping
    public List<Singer> allSingers() {
        return service.getAllSingers();
    }

    @GetMapping("/noalbums")
    public List<Singer> getAllSingersWithoutAlbums() {
        return service.getAllSingersWithoutAlbums();
    }

    @PostMapping
    public Singer create(@RequestBody Singer singer) {
        return service.createBySpringDataJpa(singer);
    }

    @PutMapping("/{id}")
    public Singer update(@PathVariable("id") long id, @RequestBody Singer singer) {
        singer.setId(id);
        return service.updateBySpringDataJpa(singer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }
}
