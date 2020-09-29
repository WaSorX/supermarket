package com.utrechtfour.supermarket.model;

import javax.validation.ValidationException;

public enum PurchaseOrderStatus {

    OPEN(0),
    FULLY_DELIVERED(1),
    CANCELED(2);

    PurchaseOrderStatus(int statusId){
        this.statusId = statusId;
    }

    private final int statusId;

    public static PurchaseOrderStatus purchaseOrderStatus(int statusId){
        switch (statusId){
            case 0: return OPEN;
            case 1: return FULLY_DELIVERED;
            case 2: return CANCELED;
            default: throw new ValidationException("PurchaseOrderStatus values should be between 0 and 2");
        }
    }

    public int getStatusId() {
        return statusId;
    }
}
