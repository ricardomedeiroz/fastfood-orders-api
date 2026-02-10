package com.ricardomedeiros.fastfoodorders.repositories;

import com.ricardomedeiros.fastfoodorders.entities.OrderItem;
import com.ricardomedeiros.fastfoodorders.entities.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {


}
