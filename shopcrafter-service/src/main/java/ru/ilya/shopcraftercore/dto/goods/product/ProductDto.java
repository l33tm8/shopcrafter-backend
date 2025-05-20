package ru.ilya.shopcraftercore.dto.goods.product;

import ru.ilya.shopcraftercore.entity.goods.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {
    private long id;

    private String name;
    private String description;
    private BigDecimal price;
    private long stock;
    private String category;
    private List<String> imageUrls;

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

    public String getCategory() {
        return category;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public static ProductDto fromEntity(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.price = product.getPrice();
        dto.stock = product.getStock();
        dto.category = product.getCategory().getName();
        dto.setImageUrls(product.getImageUrls());
        return dto;
    }
}
