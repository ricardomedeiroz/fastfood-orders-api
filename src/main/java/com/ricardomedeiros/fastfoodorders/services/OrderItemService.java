package com.ricardomedeiros.fastfoodorders.services;
import com.ricardomedeiros.fastfoodorders.entities.Menu;
import com.ricardomedeiros.fastfoodorders.entities.Order;
import com.ricardomedeiros.fastfoodorders.entities.OrderItem;
import com.ricardomedeiros.fastfoodorders.repositories.MenuRepository;
import com.ricardomedeiros.fastfoodorders.repositories.OrderItemRepository;
import com.ricardomedeiros.fastfoodorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuRepository menuRepository;



    public OrderItem addItem(Long orderId, Long menuId, Integer quantity){

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderItem item = new OrderItem();

        item.setOrder(order);
        item.setMenu(menu);
        item.setQuantity(quantity);
        item.setPrice(menu.getPrice());

        return orderItemRepository.save(item);


    }




}
