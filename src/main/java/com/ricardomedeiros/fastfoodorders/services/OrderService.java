package com.ricardomedeiros.fastfoodorders.services;
import com.ricardomedeiros.fastfoodorders.entities.Order;
import com.ricardomedeiros.fastfoodorders.enums.OrderStatus;
import com.ricardomedeiros.fastfoodorders.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;



@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order insert(Order order) {
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.RECEIVED);

        return repository.save(order);
    }

    public void delete(Long id){

        repository.deleteById(id);
    }


    public Order update(Long id, Order order){

        Order entity = repository.getReferenceById(id);
        updateData(entity, order);
        return repository.save(entity);
    }

    private void updateData(Order entity, Order order){

        entity.setStatus(order.getStatus());

    }




}
