package ru.ilya.shopcraftergoods.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<ProductVariant> variants;

    @ManyToOne
    private Category category;

    private String name;
    private String description;



}
