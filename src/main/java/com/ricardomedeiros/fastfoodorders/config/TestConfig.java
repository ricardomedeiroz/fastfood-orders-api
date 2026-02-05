package com.ricardomedeiros.fastfoodorders.config;
import com.ricardomedeiros.fastfoodorders.entities.Client;
import com.ricardomedeiros.fastfoodorders.entities.Menu;
import com.ricardomedeiros.fastfoodorders.entities.Order;
import com.ricardomedeiros.fastfoodorders.entities.OrderItem;
import com.ricardomedeiros.fastfoodorders.enums.Category;
import com.ricardomedeiros.fastfoodorders.enums.OrderStatus;
import com.ricardomedeiros.fastfoodorders.enums.PaymentStatus;
import com.ricardomedeiros.fastfoodorders.repositories.ClientRepository;
import com.ricardomedeiros.fastfoodorders.repositories.MenuRepository;
import com.ricardomedeiros.fastfoodorders.repositories.OrderItemRepository;
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
    private ClientRepository clientRepository;


    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public void run(String... args) throws Exception {

        Client c1 = new Client(null,"Pedro Gomes", "pedro@gmail.com", "777-777");
        Client c2 = new Client(null,"Julio Souza", "julio@gmail.com", "888-888");
        Client c3 = new Client(null,"Mike Jason", "mike@gmail.com", "999-999");
        clientRepository.saveAll(Arrays.asList(c1,c2,c3));

        Menu m1 = new Menu(null,"Hamburguer", "salad, cheese, bacon", 5.0, Category.BURGUER, true);
        Menu m2 = new Menu(null,"Coke", "500ml", 2.0, Category.DRINK, true);
        Menu m3 = new Menu(null,"Nutella", "200g", 5.0, Category.SIDE, true);
        menuRepository.saveAll(Arrays.asList(m1,m2,m3));

        Order o1 = new Order(null,Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.RECEIVED, PaymentStatus.PAID, c1);
        orderRepository.saveAll(Arrays.asList(o1));

        OrderItem item1 = new OrderItem(o1,m1,1,5.0);
        orderItemRepository.saveAll(Arrays.asList(item1));








    }



}
