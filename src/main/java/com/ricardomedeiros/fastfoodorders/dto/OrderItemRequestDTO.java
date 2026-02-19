package com.ricardomedeiros.fastfoodorders.dto;



public class OrderItemRequestDTO {

    Long menuItemId;
    Integer quantity;

    public OrderItemRequestDTO() {
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
