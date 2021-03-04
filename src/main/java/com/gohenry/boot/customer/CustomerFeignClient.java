package com.gohenry.boot.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "customerFeignClient", url = "http://localhost:8084")
public interface CustomerFeignClient {

    @GetMapping("/customers")
    List<Customer> getCustomers();
}
