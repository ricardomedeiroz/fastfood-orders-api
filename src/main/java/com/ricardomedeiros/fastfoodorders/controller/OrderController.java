package com.ricardomedeiros.fastfoodorders.controller;

import com.ricardomedeiros.fastfoodorders.dto.CreateOrderDTO;
import com.ricardomedeiros.fastfoodorders.entities.Order;
import com.ricardomedeiros.fastfoodorders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = orderService.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ResponseEntity.ok().body(order);

    }

    @PostMapping
    public ResponseEntity<Order> create (@RequestBody CreateOrderDTO dto){

        Order order = orderService.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }


}