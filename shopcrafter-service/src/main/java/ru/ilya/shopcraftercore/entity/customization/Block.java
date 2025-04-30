package ru.ilya.shopcraftercore.entity.customization;

import jakarta.persistence.*;

@Entity
public class Block {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String params;
    @Enumerated(value = EnumType.STRING)
    private BlockType type;

    @ManyToOne
    private Page page;

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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
