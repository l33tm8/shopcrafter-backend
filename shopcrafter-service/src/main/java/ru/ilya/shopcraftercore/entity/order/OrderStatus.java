package ru.ilya.shopcraftercore.entity.order;

public enum OrderStatus {
    CREATED,    // Оформленный (оплата совершена)
    COMPLETED,   // Завершенный (заказ доставлен)
    CANCELLED
} 