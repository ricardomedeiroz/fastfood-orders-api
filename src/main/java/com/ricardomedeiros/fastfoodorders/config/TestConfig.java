package com.ricardomedeiros.fastfoodorders.config;

import com.ricardomedeiros.fastfoodorders.entities.Order;
import com.ricardomedeiros.fastfoodorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {


    @Autowired
    private OrderRepository orderRepository;



    @Override
    public void run(String... args) throws Exception {

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"));
        Order o2 = new Order(null, Instant.parse("2019-06-21T19:53:07Z"));
        Order o3 = new Order(null, Instant.parse("2019-06-22T19:53:07Z"));

        orderRepository.saveAll(Arrays.asList(o1,o2,o3));



    }



}
