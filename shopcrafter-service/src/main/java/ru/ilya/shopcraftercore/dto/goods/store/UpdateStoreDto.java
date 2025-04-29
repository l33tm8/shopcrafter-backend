package ru.ilya.shopcraftercore.dto.goods.store;

public class UpdateStoreDto {
    private Long ownerId;
    private String name;

    public Long getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}