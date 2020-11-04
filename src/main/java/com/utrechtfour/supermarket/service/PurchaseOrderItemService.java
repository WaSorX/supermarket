package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.PurchaseOrderItem;

import java.util.Optional;

public interface PurchaseOrderItemService {

    Optional<PurchaseOrderItem> getPurchaseOrderById(Long id);

    PurchaseOrderItem createPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem);

    PurchaseOrderItem createAndUpdatePurchaseOrderItem(PurchaseOrderItem purchaseOrderItem);

    void removePurchaseOrderItemById(Long id);



}