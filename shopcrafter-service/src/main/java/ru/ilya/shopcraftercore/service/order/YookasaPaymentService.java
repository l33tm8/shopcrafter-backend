package ru.ilya.shopcraftercore.service.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.ilya.shopcraftercore.entity.order.Order;
import ru.ilya.shopcraftercore.entity.order.OrderStatus;
import ru.ilya.shopcraftercore.entity.subscription.Subscription;
import ru.ilya.shopcraftercore.repository.order.OrderRepository;
import ru.ilya.shopcraftercore.repository.subscription.SubscriptionRepository;
import ru.ilya.shopcraftercore.yookasa.AmountDto;
import ru.ilya.shopcraftercore.yookasa.PaymentRequestDto;
import ru.ilya.shopcraftercore.yookasa.PaymentResponseDto;
import ru.ilya.shopcraftercore.yookasa.YookasaRequestDto;

import java.util.UUID;

@Service
public class YookasaPaymentService {

    private static final Logger log = LoggerFactory.getLogger(YookasaPaymentService.class);
    private final RestTemplate restTemplate;
    private final OrderRepository orderRepository;
    private static final String YOOKASSA_URL = "https://api.yookassa.ru/v3/payments";
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public YookasaPaymentService(RestTemplate restTemplate, OrderRepository orderRepository, SubscriptionRepository subscriptionRepository) {
        this.restTemplate = restTemplate;
        this.orderRepository = orderRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Transactional
    public PaymentResponseDto.Confirmation makePayment(Order order) {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        AmountDto amountDto = new AmountDto();
        amountDto.setValue(order.getTotalAmount());
        amountDto.setCurrency("RUB");
        paymentRequestDto.setAmount(amountDto);
        paymentRequestDto.setConfirmation(new PaymentRequestDto.Confirmation());

        paymentRequestDto.setDescription("оплата на сайте");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Idempotence-Key", UUID.randomUUID().toString());
        HttpEntity<PaymentRequestDto> request = new HttpEntity<>(paymentRequestDto, headers);
        ResponseEntity<PaymentResponseDto> response = restTemplate.postForEntity(
                YOOKASSA_URL,
                request,
                PaymentResponseDto.class
        );
        if (!response.getStatusCode().is2xxSuccessful()) {
            log.warn("ошибка yookassa: {}", response.getBody());
            throw new RuntimeException("Ошибка на стороне Yookasa");
        }
        PaymentResponseDto paymentResponseDto = response.getBody();
        if (paymentResponseDto == null) {
            throw new RuntimeException("Ошибка со стороны Yookasa");
        }
        order.setYookasaId(paymentResponseDto.getId());
        orderRepository.save(order);
        return paymentResponseDto.getConfirmation();
    }

    @Transactional
    public PaymentResponseDto.Confirmation makeSubscriptionPayment(Subscription subscription) {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        AmountDto amountDto = new AmountDto();
        amountDto.setValue(subscription.getType().getPrice());
        amountDto.setCurrency("RUB");
        paymentRequestDto.setAmount(amountDto);
        paymentRequestDto.setConfirmation(new PaymentRequestDto.Confirmation());

        paymentRequestDto.setDescription("Оплата подписки " + subscription.getType().name());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Idempotence-Key", UUID.randomUUID().toString());
        HttpEntity<PaymentRequestDto> request = new HttpEntity<>(paymentRequestDto, headers);
        ResponseEntity<PaymentResponseDto> response = restTemplate.postForEntity(
                YOOKASSA_URL,
                request,
                PaymentResponseDto.class
        );
        if (!response.getStatusCode().is2xxSuccessful()) {
            log.warn("ошибка yookassa при оплате подписки: {}", response.getBody());
            throw new RuntimeException("Ошибка на стороне Yookasa при оплате подписки");
        }
        PaymentResponseDto paymentResponseDto = response.getBody();
        if (paymentResponseDto == null) {
            throw new RuntimeException("Ошибка со стороны Yookasa при оплате подписки");
        }
        subscription.setYookasaId(paymentResponseDto.getId());
        return paymentResponseDto.getConfirmation();
    }

    @Transactional
    public void changeOrderStatus(YookasaRequestDto dto) {
        log.info("Received request from yookassa");
        Order order = orderRepository.findByYookasaId(dto.getObject().getId());
        if (order == null) {
            return;
        }

        if ("succeeded".equals(dto.getObject().getStatus())) {
            order.setStatus(OrderStatus.COMPLETED);
            orderRepository.save(order);
        }
        else {
            order.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
        }
    }

    public void changeSubscriptionStatus(YookasaRequestDto dto) {
        Subscription subscription = subscriptionRepository.findByYookasaId(dto.getObject().getId());
        if (subscription == null) {
            return;
        }

        if ("succeeded".equals(dto.getObject().getStatus())) {
            subscription.setActive(true);
            subscriptionRepository.save(subscription);
        }
    }
}
