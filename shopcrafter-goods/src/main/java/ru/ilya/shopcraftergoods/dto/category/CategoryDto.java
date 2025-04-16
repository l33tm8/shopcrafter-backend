package ru.ilya.shopcraftergoods.dto.category;

import ru.ilya.shopcraftergoods.entity.Category;

public class CategoryDto {
    private long id;
    private String name;

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

    public static CategoryDto fromEntity(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.id = category.getId();
        dto.name = category.getName();
        return dto;
    }
}
