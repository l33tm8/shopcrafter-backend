package ru.ilya.shopcraftercore.repository.customization;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ilya.shopcraftercore.entity.customization.Block;

@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {
}
