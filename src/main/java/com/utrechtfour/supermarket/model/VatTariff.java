package com.utrechtfour.supermarket.model;

import javax.validation.ValidationException;

public enum VatTariff {
    NONE(0),
    ZERO(1),
    LOW(2),
    HIGH(3);

    private final int tariffId;

    VatTariff(int tariffId){
        this.tariffId = tariffId;
    }

    static VatTariff tariff(int tariffId){
        switch (tariffId) {
            case 0: return VatTariff.NONE;
            case 1: return VatTariff.ZERO;
            case 2: return VatTariff.LOW;
            case 3: return VatTariff.HIGH;
            default: throw new ValidationException("vatTariff values should be between 0-3");
        }
    }

    public int getTariffId() {
        return tariffId;
    }
}
