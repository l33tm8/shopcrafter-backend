package ru.ilya.shopcraftercore.entity.customization;

import jakarta.persistence.*;
import ru.ilya.shopcraftercore.entity.goods.Store;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Page {
    @Id
    @GeneratedValue
    private long id;

    private String title;

    @ManyToOne
    private Store store;

    @OneToMany
    private List<Block> blocks = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
}
