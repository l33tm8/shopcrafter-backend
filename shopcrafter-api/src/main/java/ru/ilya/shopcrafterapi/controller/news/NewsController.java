package ru.ilya.shopcrafterapi.controller.news;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.ilya.shopcraftercore.dto.news.CreateNewsDto;
import ru.ilya.shopcraftercore.dto.news.NewsDto;
import ru.ilya.shopcraftercore.service.news.NewsService;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    @Operation(summary = "Получить все новости")
    public List<NewsDto> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    @Operation(summary = "получить новость по id")
    public NewsDto getNewsById(@PathVariable Long id) {
        return newsService.getNewsById(id);
    }

    @PostMapping
    @Operation(summary = "Создать новость")
    public NewsDto createNews(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody CreateNewsDto createNewsDto
    ) {
        return newsService.createNews(userDetails, createNewsDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "обновить новость")
    public NewsDto updateNews(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            @RequestBody CreateNewsDto updateNewsDto
    ) {
        return newsService.updateNews(userDetails, id, updateNewsDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить новость")
    public void deleteNews(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id
    ) {
        newsService.deleteNews(userDetails, id);
    }
} 