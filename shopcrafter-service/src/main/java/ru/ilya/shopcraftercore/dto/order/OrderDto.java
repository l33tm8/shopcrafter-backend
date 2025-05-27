package ru.ilya.shopcraftercore.dto.order;

import ru.ilya.shopcraftercore.entity.order.Order;
import ru.ilya.shopcraftercore.entity.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {
    private Long id;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private List<OrderItemDto> items;
    private double totalAmount;
    private CustomerInfoDto customerInfo;
    private String paymentUrl;

    public static OrderDto fromEntity(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setItems(order.getItems().stream()
                .map(item -> {
                    OrderItemDto itemDto = new OrderItemDto();
                    itemDto.setProductId(item.getProduct().getId());
                    itemDto.setProductName(item.getProduct().getName());
                    itemDto.setQuantity(item.getQuantity());
                    return itemDto;
                })
                .collect(Collectors.toList()));

        if (order.getCustomerInfo() != null) {
            CustomerInfoDto customerInfoDto = new CustomerInfoDto();
            customerInfoDto.setName(order.getCustomerInfo().getName());
            customerInfoDto.setPhone(order.getCustomerInfo().getPhone());
            customerInfoDto.setEmail(order.getCustomerInfo().getEmail());
            customerInfoDto.setMessengerLink(order.getCustomerInfo().getMessengerLink());
            customerInfoDto.setDeliveryType(order.getCustomerInfo().getDeliveryType());
            customerInfoDto.setCity(order.getCustomerInfo().getCity());
            customerInfoDto.setAddress(order.getCustomerInfo().getAddress());
            customerInfoDto.setPaymentType(order.getCustomerInfo().getPaymentType());
            customerInfoDto.setComment(order.getCustomerInfo().getComment());
            dto.setCustomerInfo(customerInfoDto);
        }

        return dto;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public CustomerInfoDto getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfoDto customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }
}

