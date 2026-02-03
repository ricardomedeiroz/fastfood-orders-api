package com.ricardomedeiros.fastfoodorders.config;

import com.ricardomedeiros.fastfoodorders.entities.Client;
import com.ricardomedeiros.fastfoodorders.entities.Menu;
import com.ricardomedeiros.fastfoodorders.entities.Order;
import com.ricardomedeiros.fastfoodorders.enums.Category;
import com.ricardomedeiros.fastfoodorders.repositories.ClientRepository;
import com.ricardomedeiros.fastfoodorders.repositories.MenuRepository;
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

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public void run(String... args) throws Exception {

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"));
        Order o2 = new Order(null, Instant.parse("2019-06-21T19:53:07Z"));
        Order o3 = new Order(null, Instant.parse("2019-06-22T19:53:07Z"));

        orderRepository.saveAll(Arrays.asList(o1,o2,o3));

        Client c1 = new Client(null,"Pedro Gomes", "pedro@gmail.com", "777-777", "pedro123");
        Client c2 = new Client(null,"Isaque Carvalho", "isaque@gmail.com", "222-222", "Is@#@");
        Client c3 = new Client(null,"Maria Silva", "maria@gmail.com", "121-121", "12345");


        clientRepository.saveAll(Arrays.asList(c1,c2,c3));

        Menu m1 = new Menu(null, "Hamburguer","test", 5.0, Category.LUNCH, true);
        Menu m2 = new Menu(null, "CupCake","test", 2.0, Category.DESSERT, true);

        menuRepository.saveAll(Arrays.asList(m1,m2));
    }



}
