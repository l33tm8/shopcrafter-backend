package ru.ilya.shopcraftercore.entity.customization;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Block {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String params;
    private BlockType type;

    @ManyToOne
    private Page page;

}
