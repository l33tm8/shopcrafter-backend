package ru.ilya.shopcraftercore.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilya.shopcraftercore.entity.order.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserEmail(String email);
    Order findByYookasaId(String yookasaId);
} 