package ru.ilya.shopcraftercore.dto.goods.store;


import ru.ilya.shopcraftercore.entity.goods.Store;

import java.util.List;

public class StoreDto {

    private Long id;

    private Long ownerId;

    private List<Long> workerIds;

    private Long userId;

    private String name;

    private List<Long> categories;

    public static StoreDto fromEntity(Store store) {
        StoreDto dto = new StoreDto();
        dto.setId(store.getId());
        dto.setOwnerId(store.getOwnerId());
        dto.setWorkerIds(store.getWorkerIds());
        dto.setUserId(store.getUserId());
        dto.setName(store.getName());
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
