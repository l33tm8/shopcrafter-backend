package ru.ilya.shopcraftercore.repository.goods;

import ru.ilya.shopcraftercore.entity.goods.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
