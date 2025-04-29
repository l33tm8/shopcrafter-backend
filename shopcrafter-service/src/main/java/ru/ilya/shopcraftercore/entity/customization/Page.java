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

}
