package ru.sindm.practikum.logistic.app.domain.service;

import org.springframework.stereotype.Service;
import ru.sindm.practikum.logistic.app.domain.exception.DeliveryPriceCalculateException;
import ru.sindm.practikum.logistic.app.domain.model.DeliveryPrice;
import ru.sindm.practikum.logistic.app.domain.model.DeliveryPriceRequest;
import ru.sindm.practikum.logistic.app.domain.model.enums.CargoSizeTariff;
import ru.sindm.practikum.logistic.app.domain.model.enums.LogisticNetworkLoadTariff;

import java.util.Optional;

@Service
public class DeliveryPriceService {

    public DeliveryPrice calculateDeliveryPrice(DeliveryPriceRequest request) throws DeliveryPriceCalculateException {
        Long minDeliveryPrice = 400L;
        Long deliveryPrice = getRangeCharge(request.getDeliveryRange())
                + getFragileCharge(request.getFragile(), request.getDeliveryRange())
                + getCargoSizeCharge(request.getCargoSize());
        deliveryPrice = getLogisticNetworkLoadCharge(deliveryPrice, request.getLogisticNetworkLoadState());
        return new DeliveryPrice((deliveryPrice < minDeliveryPrice) ? minDeliveryPrice : deliveryPrice);
    }

    private Long getRangeCharge(Double range) {
        if (range >= 30) {
            return 300L;
        } else if (range >= 10) {
            return 200L;
        } else if (range >= 2) {
            return 100L;
        } else {
            return 50L;
        }
    }

    private Long getFragileCharge(Boolean isFragile, Double range) throws DeliveryPriceCalculateException {
        if (isFragile && range > 30) {
            throw new DeliveryPriceCalculateException("Fragile cargo cannot be delivered over a distance of more than 30 km");
        }
        return isFragile ? 300L : 0L;
    }

    private Long getCargoSizeCharge(String cargoSize) throws DeliveryPriceCalculateException {
        CargoSizeTariff tariff = Optional.of(CargoSizeTariff.getTariffRateByCargoSize(cargoSize))
                .orElseThrow(() -> new DeliveryPriceCalculateException("Invalid cargo size"));
        return tariff.getTariffRate();
    }

    private Long getLogisticNetworkLoadCharge(Long price, String logisticNetworkLoad) throws DeliveryPriceCalculateException {
        LogisticNetworkLoadTariff tariff = Optional.of(LogisticNetworkLoadTariff.getByLoadState(logisticNetworkLoad))
                .orElseThrow(() -> new DeliveryPriceCalculateException("Invalid delivery network load"));
        return (long) (price * tariff.getRatio());
    }
}
