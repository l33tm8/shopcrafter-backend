package dto;

import ru.ilya.shopcraftergoods.entity.Category;
import ru.ilya.shopcraftergoods.entity.Store;

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
        dto.setName(store.getName());
        dto.setCategories(store.getCategories().stream().map(Category::getId).toList());
        dto.setWorkerIds(store.getWorkerIds());
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
