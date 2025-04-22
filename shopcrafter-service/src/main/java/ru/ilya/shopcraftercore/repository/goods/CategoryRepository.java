package ru.ilya.shopcraftercore.repository.goods;

import ru.ilya.shopcraftercore.entity.goods.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ilya.shopcraftercore.entity.goods.Store;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByStoreIdAndId(Long storeId, Long id);
    List<Category> findByStoreId(Long storeId);
    void deleteByStoreIdAndId(Long storeId, Long id);
}
