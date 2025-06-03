package ru.ilya.shopcraftercore.service.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilya.shopcraftercore.entity.subscription.Subscription;
import ru.ilya.shopcraftercore.entity.subscription.SubscriptionType;
import ru.ilya.shopcraftercore.entity.auth.User;
import ru.ilya.shopcraftercore.repository.subscription.SubscriptionRepository;
import ru.ilya.shopcraftercore.service.order.YookasaPaymentService;
import ru.ilya.shopcraftercore.yookasa.PaymentResponseDto;
import ru.ilya.shopcraftercore.yookasa.YookasaRequestDto;

import java.time.LocalDateTime;

@Service
public class SubscriptionService {

    private final YookasaPaymentService yookasaPaymentService;
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(YookasaPaymentService yookasaPaymentService,
                             SubscriptionRepository subscriptionRepository) {
        this.yookasaPaymentService = yookasaPaymentService;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Transactional
    public PaymentResponseDto.Confirmation purchaseSubscription(User user, SubscriptionType type) {
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setType(type);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(LocalDateTime.now().plusMonths(1));
        subscription.setActive(true);
        
        PaymentResponseDto.Confirmation confirmation = yookasaPaymentService.makeSubscriptionPayment(subscription);
        subscriptionRepository.save(subscription);
        
        return confirmation;
    }

    @Transactional
    public void handlePaymentNotification(YookasaRequestDto dto) {
        Subscription subscription = subscriptionRepository.findByYookasaId(dto.getObject().getId());
        if (subscription == null) {
            return;
        }

        if ("succeeded".equals(dto.getObject().getStatus())) {
            subscription.setActive(true);
        } else {
            subscription.setActive(false);
        }
        
        subscriptionRepository.save(subscription);
    }
} 