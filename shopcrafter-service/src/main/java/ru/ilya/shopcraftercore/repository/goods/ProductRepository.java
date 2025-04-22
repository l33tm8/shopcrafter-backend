package ru.ilya.shopcraftercore.repository.goods;

import ru.ilya.shopcraftercore.entity.goods.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByCategoryId(long categoryId);
    Product findByCategoryIdAndId(Long categoryId, Long id);
}
