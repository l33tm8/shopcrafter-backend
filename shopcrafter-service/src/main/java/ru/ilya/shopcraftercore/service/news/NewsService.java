package ru.ilya.shopcraftercore.service.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilya.shopcraftercore.dto.news.CreateNewsDto;
import ru.ilya.shopcraftercore.dto.news.NewsDto;
import ru.ilya.shopcraftercore.entity.auth.User;
import ru.ilya.shopcraftercore.entity.news.News;
import ru.ilya.shopcraftercore.exception.EntityNotFoundException;
import ru.ilya.shopcraftercore.exception.ForbiddenException;
import ru.ilya.shopcraftercore.repository.auth.UserRepository;
import ru.ilya.shopcraftercore.repository.news.NewsRepository;

import java.util.List;
import java.util.Objects;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    public List<NewsDto> getAllNews() {
        return newsRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(NewsDto::fromEntity)
                .toList();
    }

    public NewsDto getNewsById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News not found"));
        return NewsDto.fromEntity(news);
    }

    @Transactional
    public NewsDto createNews(UserDetails userDetails, CreateNewsDto createNewsDto) {
        User author = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        News news = new News();
        news.setTitle(createNewsDto.getTitle());
        news.setContent(createNewsDto.getContent());
        news.setAuthor(author);

        return NewsDto.fromEntity(newsRepository.save(news));
    }

    @Transactional
    public NewsDto updateNews(UserDetails userDetails, Long id, CreateNewsDto updateNewsDto) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News not found"));
        
        if (!Objects.equals(news.getAuthor().getEmail(), userDetails.getUsername())) {
            throw new ForbiddenException("You don't have permission to update this news");
        }

        news.setTitle(updateNewsDto.getTitle());
        news.setContent(updateNewsDto.getContent());

        return NewsDto.fromEntity(newsRepository.save(news));
    }

    @Transactional
    public void deleteNews(UserDetails userDetails, Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News not found"));
        
        if (!Objects.equals(news.getAuthor().getEmail(), userDetails.getUsername())) {
            throw new ForbiddenException("You don't have permission to delete this news");
        }

        newsRepository.delete(news);
    }
} 