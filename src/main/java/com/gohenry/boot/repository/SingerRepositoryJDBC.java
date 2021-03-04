package com.gohenry.boot.repository;

import com.gohenry.boot.ApplicationProperties;
import com.gohenry.boot.domain.model.Singer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SingerRepositoryJDBC {

    private String templateCode = "SAVING_PARENT";

    private final JdbcTemplate jdbcTemplate;

    private final ApplicationProperties properties;

    public Singer create(Singer singer) {
        int update = jdbcTemplate.update("insert into singer(firstName, lastName, birthDate) values (?, ?, ?)",
                singer.getFirstName(),
                singer.getLastName(),
                singer.getBirthDate());
        if (update != 1) {
            throw new IllegalArgumentException("Bad update");
        }

        return getAllSingers().stream()
                              .max(Comparator.comparing(Singer::getId))
                              .get();
    }

    public List<Singer> getAllSingers() {
        RowMapper<Singer> singerRowMapper = (rs, rowNum) ->
                Singer.builder()
                      .id(rs.getLong("id"))
                      .firstName(rs.getString("firstName"))
                      .lastName(rs.getString("lastName"))
                      .birthDate(rs.getDate("birthDate").toLocalDate())
                      .build();
        System.out.println("===========test: " + properties.getTest());
        System.out.println("===========firebase user:" + properties.getFirebase().getUser());

        return jdbcTemplate.query("select * from singer",
                singerRowMapper);
    }

    public Singer update(Singer singer) {
        int update = jdbcTemplate.update(
                "update singer set " +
                        "firstName = ?, " +
                        "lastName = ?, " +
                        "birthDate = ? where id = ? ",
                singer.getFirstName(),
                singer.getLastName(),
                singer.getBirthDate(),
                singer.getId());

        if (update != 1) {
            throw new IllegalArgumentException("Bad update");
        }

        return getAllSingers().stream()
                              .filter(s -> s.getId() == singer.getId())
                              .findFirst()
                              .get();
    }
}
