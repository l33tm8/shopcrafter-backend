package ru.ilya.shopcraftercore.repository.news;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ilya.shopcraftercore.entity.news.News;

import java.util.List;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
    List<News> findAllByOrderByCreatedAtDesc();
} 