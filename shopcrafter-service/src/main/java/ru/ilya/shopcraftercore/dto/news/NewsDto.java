package ru.ilya.shopcraftercore.dto.news;

import ru.ilya.shopcraftercore.entity.news.News;

import java.time.LocalDateTime;

public class NewsDto {
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private LocalDateTime createdAt;

    public static NewsDto fromEntity(News news) {
        NewsDto dto = new NewsDto();
        dto.setId(news.getId());
        dto.setTitle(news.getTitle());
        dto.setContent(news.getContent());
        dto.setAuthorName(news.getAuthor().getEmail());
        dto.setCreatedAt(news.getCreatedAt());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
} 