package ru.sindm.practikum.logistic.app.domain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sindm.practikum.logistic.app.domain.exception.DeliveryPriceCalculateException;
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
    public ResponseEntity<?> calculateDeliveryPrice(@RequestBody @Valid DeliveryPriceRequest request) throws DeliveryPriceCalculateException {
        return new ResponseEntity<>(deliveryPriceService.calculateDeliveryPrice(request), HttpStatus.OK);
    }
}