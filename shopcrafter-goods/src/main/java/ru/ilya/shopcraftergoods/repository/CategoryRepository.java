package ru.ilya.shopcraftergoods.repository;

import ru.ilya.shopcraftergoods.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
