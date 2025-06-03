package ru.ilya.shopcrafterapi.controller.subscription;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ilya.shopcrafterapi.service.UserService;
import ru.ilya.shopcraftercore.entity.auth.User;
import ru.ilya.shopcraftercore.entity.subscription.SubscriptionType;
import ru.ilya.shopcraftercore.service.subscription.SubscriptionService;
import ru.ilya.shopcraftercore.yookasa.PaymentResponseDto;

@RestController
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final UserService userService;
    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    @Operation(description = "Купить подписку")
    @PostMapping("/{$subscriptionType}")
    public PaymentResponseDto.Confirmation purchaseSubscription(@PathVariable SubscriptionType subscriptionType, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByDetails(userDetails);
        return subscriptionService.purchaseSubscription(user, subscriptionType);
    }

}
