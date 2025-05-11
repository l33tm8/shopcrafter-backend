package ru.ilya.shopcrafterapi.controller.basket;

import org.springframework.web.bind.annotation.*;
import ru.ilya.shopcraftercore.dto.basket.BasketDto;
import ru.ilya.shopcraftercore.service.basket.BasketService;

@RestController
@RequestMapping("/users/{userId}/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping
    public BasketDto getBasket(@PathVariable long userId) {
        return basketService.getUserBasket(userId);
    }

    @PatchMapping("/products/{productId}")
    public BasketDto addProductToBasket(@PathVariable long userId, @PathVariable long productId) {
        return basketService.addProductToBasket(userId, productId);
    }

    @DeleteMapping("/products/{productId}")
    public BasketDto deleteProductFromBasket(@PathVariable long userId, @PathVariable long productId) {
        return basketService.removeProductFromBasket(userId, productId);
    }
}
