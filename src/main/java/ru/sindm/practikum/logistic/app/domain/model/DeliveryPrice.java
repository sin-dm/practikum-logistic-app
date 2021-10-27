package ru.sindm.practikum.logistic.app.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryPrice {

    @JsonProperty("deliveryPrice")
    private Long deliveryPrice;

    public DeliveryPrice(Long calculateDeliveryPrice) {
        this.deliveryPrice = calculateDeliveryPrice;
    }
}
