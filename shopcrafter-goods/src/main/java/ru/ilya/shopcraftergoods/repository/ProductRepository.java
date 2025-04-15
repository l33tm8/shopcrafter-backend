package ru.ilya.shopcraftergoods.repository;

import ru.ilya.shopcraftergoods.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
