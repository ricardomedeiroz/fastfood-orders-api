package com.ricardomedeiros.fastfoodorders.services;
import com.ricardomedeiros.fastfoodorders.dto.CreateOrderDTO;
import com.ricardomedeiros.fastfoodorders.dto.OrderItemRequestDTO;
import com.ricardomedeiros.fastfoodorders.entities.Client;
import com.ricardomedeiros.fastfoodorders.entities.Order;
import com.ricardomedeiros.fastfoodorders.entities.OrderItem;
import com.ricardomedeiros.fastfoodorders.enums.OrderStatus;
import com.ricardomedeiros.fastfoodorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;



@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private ClientService clientService;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }



    public void delete(Long id){

        repository.deleteById(id);
    }




    public void updateData(Order entity, Order order){

        entity.setStatus(order.getStatus());

    }

    public Order createOrder(CreateOrderDTO dto){

        Client client = clientService.findById(dto.getClientId());
        Order order = new Order();
        order.setClient(client);
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.RECEIVED);
        return repository.save(order);


    }




}
