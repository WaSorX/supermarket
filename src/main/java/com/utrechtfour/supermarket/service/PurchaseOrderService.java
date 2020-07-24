package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.dto.PurchaseOrderDto;
import com.utrechtfour.supermarket.dto.PurchaseOrderItemDto;

import java.math.BigDecimal;

public interface PurchaseOrderService {

    PurchaseOrderDto createPurchaseOrder(PurchaseOrderDto purchaseOrderDto);

    PurchaseOrderDto updatePurchaseOrder(PurchaseOrderItemDto purchaseOrderItemDto, Long id);

}
