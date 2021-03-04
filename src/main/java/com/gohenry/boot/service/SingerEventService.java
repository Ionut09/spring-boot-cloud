package com.gohenry.boot.service;

import com.gohenry.boot.customer.Customer;
import com.gohenry.boot.customer.CustomerFeignClient;
import com.gohenry.boot.domain.entity.SingerEventEntity;
import com.gohenry.boot.domain.model.SingerEvent;
import com.gohenry.boot.repository.EventRepository;
import com.gohenry.boot.stream.Channels;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;

@Service
@EnableBinding(Channels.class)
@AllArgsConstructor
public class SingerEventService {

    private final EventRepository repository;

    private final CustomerFeignClient customerFeignClient;

    @StreamListener(Channels.INPUT)
    public void handleSingerEvent(SingerEvent singerEvent, @Header("x-timestamp") Long time) {
        System.out.println(time);
        repository.save(SingerEventEntity.builder()
                                         .singerId(singerEvent.getSingerId())
                                         .created(singerEvent.getCreated())
                                         .type(singerEvent.getType())
                                         .build());

        List<Customer> customers = customerFeignClient.getCustomers();
        customers.forEach(System.out::println);
    }
}
