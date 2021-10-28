package ru.sindm.practikum.logistic.app.domain.controller;

import org.springframework.web.bind.annotation.*;
import ru.sindm.practikum.logistic.app.domain.common.model.Response;
import ru.sindm.practikum.logistic.app.domain.exception.DeliveryPriceCalculateException;
import ru.sindm.practikum.logistic.app.domain.model.DeliveryPrice;
import ru.sindm.practikum.logistic.app.domain.model.DeliveryPriceRequest;
import ru.sindm.practikum.logistic.app.domain.service.DeliveryPriceService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/delivery")
public class DeliveryController {

    private final DeliveryPriceService deliveryPriceService;

    public DeliveryController(DeliveryPriceService deliveryPriceService) {
        this.deliveryPriceService = deliveryPriceService;
    }

    @PostMapping("/price/calculate")
    public Response<DeliveryPrice> calculateDeliveryPrice(@RequestBody @Valid DeliveryPriceRequest request) throws DeliveryPriceCalculateException {
        return new Response<>(200, deliveryPriceService.calculateDeliveryPrice(request));
    }
}