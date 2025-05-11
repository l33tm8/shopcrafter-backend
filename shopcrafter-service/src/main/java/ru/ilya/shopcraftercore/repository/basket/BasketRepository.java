package ru.ilya.shopcraftercore.repository.basket;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ilya.shopcraftercore.entity.basket.Basket;

import java.util.Optional;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {
    Optional<Basket> findByBasketOwnerId(Long id);
}
