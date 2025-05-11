package ru.ilya.shopcraftercore.service.basket;

import org.springframework.stereotype.Service;
import ru.ilya.shopcraftercore.dto.basket.BasketDto;
import ru.ilya.shopcraftercore.entity.basket.Basket;
import ru.ilya.shopcraftercore.entity.goods.Product;
import ru.ilya.shopcraftercore.exception.EntityNotFoundException;
import ru.ilya.shopcraftercore.repository.basket.BasketRepository;
import ru.ilya.shopcraftercore.repository.goods.ProductRepository;

import java.util.Optional;

@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    public BasketService(BasketRepository basketRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }

    public BasketDto getUserBasket(long userId) {
        Optional<Basket> basket = basketRepository.findById(userId);
        return basket.map(BasketDto::fromBasket).orElseThrow(() -> new EntityNotFoundException("Basket not found"));
    }

    public BasketDto addProductToBasket(long userId, long productId) {
        Optional<Basket> basketOptional = basketRepository.findById(userId);
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }
        if (basketOptional.isPresent()) {
            Basket basket = basketOptional.get();
            basket.addProduct(productOptional.get());
            basketRepository.save(basket);
            return BasketDto.fromBasket(basket);
        }
        throw new EntityNotFoundException("Basket not found");
    }

    public BasketDto removeProductFromBasket(long userId, long productId) {
        Optional<Basket> basketOptional = basketRepository.findById(userId);
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }
        if (basketOptional.isPresent()) {
            Basket basket = basketOptional.get();
            basket.deleteProduct(productOptional.get());
            basketRepository.save(basket);
            return BasketDto.fromBasket(basket);
        }
        throw new EntityNotFoundException("Basket not found");
    }
}
