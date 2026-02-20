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
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
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

    @Transactional
    public Order updateOrderItems(long orderId, List<OrderItemRequestDTO>itemsDto ){

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + orderId));


        List<OrderItem> newItems = new ArrayList<>();
        for (OrderItemRequestDTO dto : itemsDto){

         Menu menuItem = menuService.findById(dto.getMenuItemId());

         OrderItem item = new OrderItem();
         item.setOrder(order);
         item.setMenu(menuItem);
         item.setQuantity(dto.getQuantity());
         item.setPrice(menuItem.getPrice());

         newItems.add(item);

        }

        order.updateItems(newItems);

        return orderRepository.save(order);
    }



    public void delete(Long id){

        orderRepository.deleteById(id);
    }

    @Transactional
    public Order cancelOrder(Long orderId){

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + orderId));

        order.cancel();

        return orderRepository.save(order);

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
