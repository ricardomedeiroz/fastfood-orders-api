package com.ricardomedeiros.fastfoodorders.entities;

import com.ricardomedeiros.fastfoodorders.dto.OrderItemRequestDTO;
import com.ricardomedeiros.fastfoodorders.enums.OrderStatus;
import com.ricardomedeiros.fastfoodorders.enums.PaymentStatus;
import com.ricardomedeiros.fastfoodorders.exceptions.ActionNotAllowedException;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant moment;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {
    }

    public Order(Long id, Instant moment, OrderStatus status, PaymentStatus paymentStatus, Client client) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Double getTotal() {
        double sum = 0.0;
        for (OrderItem item : items) {
            sum += item.subTotal();
        }
        return sum;
    }

    public void cancel(){

        if (getStatus() == OrderStatus.RECEIVED){
            setStatus(OrderStatus.CANCELED);

        }else  {

            throw new ActionNotAllowedException("Order cannot be cancelled in status " + getStatus());

        }

    }

    public void updateItems(List<OrderItemRequestDTO> items){



    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
