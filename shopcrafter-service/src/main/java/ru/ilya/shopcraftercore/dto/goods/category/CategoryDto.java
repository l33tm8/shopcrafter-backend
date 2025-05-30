package ru.ilya.shopcraftercore.dto.goods.category;

import ru.ilya.shopcraftercore.entity.goods.Category;

public class CategoryDto {
    private long id;
    private String name;
    private String imageUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static CategoryDto fromEntity(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.id = category.getId();
        dto.name = category.getName();
        dto.setImageUrl(category.getImageUrl());
        return dto;
    }
}
