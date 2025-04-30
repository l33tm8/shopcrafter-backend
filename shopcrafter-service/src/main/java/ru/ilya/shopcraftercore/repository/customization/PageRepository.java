package ru.ilya.shopcraftercore.repository.customization;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ilya.shopcraftercore.entity.customization.Page;

import java.util.List;

@Repository
public interface PageRepository extends CrudRepository<Page, Long> {
    List<Page> findByStoreId(long storeId);
}
