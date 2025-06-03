package ru.ilya.shopcrafterapi.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ilya.shopcraftercore.service.order.YookasaPaymentService;
import ru.ilya.shopcraftercore.yookasa.YookasaRequestDto;

@RestController
@RequestMapping("/yookassa/pay")
public class YookasaController {
    private final YookasaPaymentService yookasaPaymentService;

    @Autowired
    public YookasaController(final YookasaPaymentService yookasaPaymentService) {
        this.yookasaPaymentService = yookasaPaymentService;
    }

    @PostMapping
    @Operation(description = "эндпоинт для юкассы, не трогать")
    public void serveYookasa(@RequestBody YookasaRequestDto dto) {
        yookasaPaymentService.changeOrderStatus(dto);
        yookasaPaymentService.changeSubscriptionStatus(dto);
    }
}
