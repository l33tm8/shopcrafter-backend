package ru.ilya.shopcraftercore.entity.subscription;

public enum SubscriptionType {
    STANDARD(990.0),
    PRO(2990.0);

    private final double price;

    SubscriptionType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
} 