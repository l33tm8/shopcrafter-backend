package ru.ilya.shopcraftercore.repository.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ilya.shopcraftercore.entity.order.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    List<OrderItem> findByProductId(Long productId);
} 