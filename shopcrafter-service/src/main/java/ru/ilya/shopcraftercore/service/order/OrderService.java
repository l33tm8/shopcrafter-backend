package ru.ilya.shopcraftercore.service.order;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilya.shopcraftercore.dto.order.CreateOrderDto;
import ru.ilya.shopcraftercore.dto.order.OrderDto;
import ru.ilya.shopcraftercore.dto.order.OrderItemDto;
import ru.ilya.shopcraftercore.entity.goods.Product;
import ru.ilya.shopcraftercore.entity.order.CustomerInfo;
import ru.ilya.shopcraftercore.entity.order.Order;
import ru.ilya.shopcraftercore.entity.order.OrderItem;
import ru.ilya.shopcraftercore.entity.order.OrderStatus;
import ru.ilya.shopcraftercore.repository.order.OrderRepository;
import ru.ilya.shopcraftercore.repository.goods.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                       ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);

        CustomerInfo customerInfo = getCustomerInfo(createOrderDto, order);
        order.setCustomerInfo(customerInfo);

        double totalAmount = 0;
        for (OrderItemDto itemDto : createOrderDto.getProducts()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setTotalPrice(product.getPrice().longValue() * itemDto.getQuantity());
            order.getItems().add(orderItem);

            totalAmount += orderItem.getTotalPrice();
        }
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);
        return OrderDto.fromEntity(savedOrder);
    }

    private static CustomerInfo getCustomerInfo(CreateOrderDto createOrderDto, Order order) {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setName(createOrderDto.getCustomerInfo().getName());
        customerInfo.setPhone(createOrderDto.getCustomerInfo().getPhone());
        customerInfo.setEmail(createOrderDto.getCustomerInfo().getEmail());
        customerInfo.setMessengerLink(createOrderDto.getCustomerInfo().getMessengerLink());
        customerInfo.setDeliveryType(createOrderDto.getCustomerInfo().getDeliveryType());
        customerInfo.setCity(createOrderDto.getCustomerInfo().getCity());
        customerInfo.setAddress(createOrderDto.getCustomerInfo().getAddress());
        customerInfo.setPaymentType(createOrderDto.getCustomerInfo().getPaymentType());
        customerInfo.setComment(createOrderDto.getCustomerInfo().getComment());
        customerInfo.setOrder(order);
        return customerInfo;
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getOrders() {
        return orderRepository.findAll().stream()
                .map(OrderDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return OrderDto.fromEntity(order);
    }

    @Transactional
    public OrderDto updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setStatus(newStatus);
        Order updatedOrder = orderRepository.save(order);
        return OrderDto.fromEntity(updatedOrder);
    }
} 