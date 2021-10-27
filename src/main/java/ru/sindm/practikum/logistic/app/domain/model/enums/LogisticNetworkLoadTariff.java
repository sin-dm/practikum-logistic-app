package ru.sindm.practikum.logistic.app.domain.model.enums;

import java.util.Arrays;

public enum LogisticNetworkLoadTariff {
    DEFAULT("default", 1.0),
    HIGH("high", 1.2),
    VERY_HIGH("veryHigh", 1.4),
    CRITICAL("critical", 1.6);

    private final String loadState;
    private final Double ratio;

    LogisticNetworkLoadTariff(String loadState, Double ratio) {
        this.loadState = loadState;
        this.ratio = ratio;
    }

    public Double getRatio() {
        return ratio;
    }

    public static LogisticNetworkLoadTariff getByLoadState(String loadState) {
        return Arrays.stream(LogisticNetworkLoadTariff.values()).filter(
                item -> item.loadState.equals(loadState)).findFirst().orElse(null);
    }
}
