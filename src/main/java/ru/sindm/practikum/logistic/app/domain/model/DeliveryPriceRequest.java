package ru.sindm.practikum.logistic.app.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DeliveryPriceRequest {

    @NotNull(message = "Delivery range cannot be null")
    @Positive(message = "Delivery range must be positive")
    @JsonProperty(value = "deliveryRange")
    private Double deliveryRange;

    @NotNull(message = "Cargo size cannot be null")
    @JsonProperty(value = "cargoSize")
    private String cargoSize;

    @NotNull(message = "Fragile flag cannot be null")
    @JsonProperty(value = "fragile")
    private Boolean isFragile;

    @JsonProperty(value = "logisticNetworkLoadState")
    private String logisticNetworkLoadState = "default";

    public Double getDeliveryRange() {
        return deliveryRange;
    }

    public String getCargoSize() {
        return cargoSize;
    }

    public Boolean getFragile() {
        return isFragile;
    }

    public String getLogisticNetworkLoadState() {
        return logisticNetworkLoadState;
    }
}
