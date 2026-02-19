package com.ricardomedeiros.fastfoodorders.services;
import com.ricardomedeiros.fastfoodorders.dto.CreateOrderDTO;
import com.ricardomedeiros.fastfoodorders.dto.OrderItemRequestDTO;
import com.ricardomedeiros.fastfoodorders.entities.Client;
import com.ricardomedeiros.fastfoodorders.entities.Menu;
import com.ricardomedeiros.fastfoodorders.entities.Order;
import com.ricardomedeiros.fastfoodorders.entities.OrderItem;
import com.ricardomedeiros.fastfoodorders.enums.OrderStatus;
import com.ricardomedeiros.fastfoodorders.enums.PaymentStatus;
import com.ricardomedeiros.fastfoodorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;



@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private MenuService menuService;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }



    public void delete(Long id){

        orderRepository.deleteById(id);
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
        order.setPaymentStatus(PaymentStatus.PENDING);

        for (OrderItemRequestDTO itemDTO : dto.getItems()) {

            Menu menuItem = menuService.findById(itemDTO.getMenuItemId());


            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMenu(menuItem);


            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(menuItem.getPrice());


            order.getItems().add(orderItem);


        }
        return orderRepository.save(order);




    }




}
