package ru.ilya.shopcraftercore.dto.customization;

import ru.ilya.shopcraftercore.entity.customization.Block;
import ru.ilya.shopcraftercore.entity.customization.BlockType;

public class BlockDto {
    private String name;
    private String params;
    private String BlockType;

    public static BlockDto fromEntity(Block entity) {
        BlockDto dto = new BlockDto();
        dto.setName(entity.getName());
        dto.setParams(entity.getParams());
        dto.setBlockType(entity.getType().name());
        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getBlockType() {
        return BlockType;
    }

    public void setBlockType(String blockType) {
        BlockType = blockType;
    }
}
