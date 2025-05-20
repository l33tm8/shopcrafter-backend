package ru.ilya.shopcraftercore.dto.goods.store;


import ru.ilya.shopcraftercore.entity.goods.Category;
import ru.ilya.shopcraftercore.entity.goods.Store;

import java.util.List;

public class StoreDto {

    private Long id;

    private Long ownerId;

    private List<Long> workerIds;


    private String name;

    private String description;

    private List<Long> categories;

    private String imageUrl;

    public static StoreDto fromEntity(Store store) {
        StoreDto dto = new StoreDto();
        dto.setId(store.getId());
        dto.setOwnerId(store.getOwner().getId());
        List<Long> workers = store.getWorkers() == null ? null : store.getWorkers().stream()
                .map(worker -> worker.getUser().getId())
                .toList();
        dto.setWorkerIds(workers);
        dto.setName(store.getName());
        dto.setCategories(store.getCategories() == null ? null : store.getCategories().stream()
                .map(Category::getId)
                .toList());
        dto.setDescription(store.getDescription());
        dto.setImageUrl(store.getImageUrl());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<Long> getWorkerIds() {
        return workerIds;
    }

    public void setWorkerIds(List<Long> workerIds) {
        this.workerIds = workerIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
