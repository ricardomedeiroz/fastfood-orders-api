package com.ricardomedeiros.fastfoodorders.dto;

import java.util.List;

public class CreateOrderDTO {

    Long clientId;
    List<OrderItemRequestDTO> items;

    public CreateOrderDTO() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }
}


