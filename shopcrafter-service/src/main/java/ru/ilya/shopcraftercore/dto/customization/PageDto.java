package ru.ilya.shopcraftercore.dto.customization;

import ru.ilya.shopcraftercore.entity.customization.Block;
import ru.ilya.shopcraftercore.entity.customization.Page;

import java.util.ArrayList;
import java.util.List;

public class PageDto {
    private long id;

    private String title;

    private long storeId;

    private List<Long> blocksIds;

    public static PageDto fromEntity(Page entity) {
        PageDto dto = new PageDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        List<Block> blocks = entity.getBlocks();
        List<Long> blocksIds = new ArrayList<>();
        if (blocks != null) {
            for (Block block : blocks) {
                blocksIds.add(block.getId());
            }
        }
        dto.setBlocksIds(blocksIds);
        dto.setStoreId(entity.getStore().getId());
        return dto;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getStoreId() {
        return storeId;
    }

    public List<Long> getBlocksIds() {
        return blocksIds;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public void setBlocksIds(List<Long> blocksIds) {
        this.blocksIds = blocksIds;
    }
}
