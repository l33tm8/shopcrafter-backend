package ru.ilya.shopcraftercore.dto.goods.product;

import java.math.BigDecimal;

public class PutProductDto {
    private String name;
    private String description;
    private BigDecimal price;

    private long stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getStock() {
        return stock;
    }
}
