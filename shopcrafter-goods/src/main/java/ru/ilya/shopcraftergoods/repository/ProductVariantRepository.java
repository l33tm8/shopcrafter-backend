package ru.ilya.shopcraftergoods.repository;

import ru.ilya.shopcraftergoods.entity.ProductVariant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepository extends CrudRepository<ProductVariant, Long> {
}
