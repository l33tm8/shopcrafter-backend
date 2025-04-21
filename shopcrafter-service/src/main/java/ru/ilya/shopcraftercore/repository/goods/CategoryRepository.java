package ru.ilya.shopcraftercore.repository.goods;

import ru.ilya.shopcraftercore.entity.goods.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
