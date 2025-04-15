package ru.ilya.shopcraftergoods.repository;

import ru.ilya.shopcraftergoods.entity.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {
}
