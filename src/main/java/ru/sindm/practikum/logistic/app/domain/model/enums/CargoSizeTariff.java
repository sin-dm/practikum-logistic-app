package ru.sindm.practikum.logistic.app.domain.model.enums;

import java.util.Arrays;

public enum CargoSizeTariff {
    SMALL("small", 100L),
    LARGE("large", 200L);

    private final String cargoSize;
    private final Long tariffRate;

    CargoSizeTariff(String cargoSize, Long tariffRate) {
        this.cargoSize = cargoSize;
        this.tariffRate = tariffRate;
    }

    public Long getTariffRate() {
        return this.tariffRate;
    }

    public static CargoSizeTariff getTariffRateByCargoSize(String cargoSize) {
        return Arrays.stream(CargoSizeTariff.values()).filter(item -> item.cargoSize.equals(cargoSize)).findFirst().orElse(null);
    }
}
