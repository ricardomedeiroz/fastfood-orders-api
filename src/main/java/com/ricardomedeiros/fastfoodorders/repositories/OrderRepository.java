package com.ricardomedeiros.fastfoodorders.repositories;

import com.ricardomedeiros.fastfoodorders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
