package ru.ilya.shopcraftercore.repository.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilya.shopcraftercore.entity.subscription.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByYookasaId(String yookasaId);
} 