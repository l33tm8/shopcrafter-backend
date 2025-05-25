package ru.ilya.shopcraftercore.dto.order;

import java.util.List;

public class CreateOrderDto {
    private List<OrderItemDto> products;
    private CustomerInfoDto customerInfo;

    public List<OrderItemDto> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItemDto> products) {
        this.products = products;
    }

    public CustomerInfoDto getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfoDto customerInfo) {
        this.customerInfo = customerInfo;
    }
}

