package ru.ilya.shopcraftercore.entity.basket;

import jakarta.persistence.*;
import ru.ilya.shopcraftercore.entity.auth.User;
import ru.ilya.shopcraftercore.entity.goods.Product;

import java.util.List;

@Entity
public class Basket {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private User basketOwner;

    @OneToMany
    private List<Product> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getBasketOwner() {
        return basketOwner;
    }

    public void setBasketOwner(User basketOwner) {
        this.basketOwner = basketOwner;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
