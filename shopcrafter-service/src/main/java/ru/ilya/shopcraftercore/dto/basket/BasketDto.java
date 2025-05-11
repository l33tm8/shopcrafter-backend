package ru.ilya.shopcraftercore.dto.basket;

import ru.ilya.shopcraftercore.dto.auth.UserDto;
import ru.ilya.shopcraftercore.dto.goods.product.ProductDto;
import ru.ilya.shopcraftercore.entity.basket.Basket;
import ru.ilya.shopcraftercore.entity.goods.Product;

import java.util.LinkedList;
import java.util.List;

public class BasketDto {
    private long id;

    private UserDto user;

    private List<ProductDto> products;

    public static BasketDto fromBasket(Basket basket) {
        BasketDto dto = new BasketDto();
        dto.id = basket.getId();
        dto.user = UserDto.fromUser(basket.getBasketOwner());
        List<Product> products = basket.getProducts();
        if (products != null && !products.isEmpty()) {
            List<ProductDto> productsDto = new LinkedList<>();
            for (Product product : products) {
                productsDto.add(ProductDto.fromEntity(product));
            }
            dto.products = productsDto;
        }
        return dto;
    }

    public long getId() {
        return id;
    }

    public UserDto getUser() {
        return user;
    }

    public List<ProductDto> getProducts() {
        return products;
    }
}
