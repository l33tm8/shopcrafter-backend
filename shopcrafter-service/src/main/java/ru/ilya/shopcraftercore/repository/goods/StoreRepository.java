package ru.ilya.shopcraftercore.repository.goods;

import ru.ilya.shopcraftercore.entity.goods.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {
}
