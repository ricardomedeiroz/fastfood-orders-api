package com.ricardomedeiros.fastfoodorders.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ricardomedeiros.fastfoodorders.entities.pk.OrderItemPK;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private Double price;

    public OrderItem() {
    }

    public OrderItem(Order order, Menu menu, Integer quantity, Double price) {
        id.setOrder(order);
        id.setMenu(menu);
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    public Order getOrder(){
        return id.getOrder();
    }

    public void setOrder(Order order){
        id.setOrder(order);
    }

    @ManyToOne
    @MapsId("menuId")
    @JoinColumn(name = "menu_id")
    public Menu getMenu(){
        return id.getMenu();
    }

    public void setMenu(Menu menu){
        id.setMenu(menu);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double subTotal(){

        return price * quantity;

    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
