package com.utrechtfour.supermarket.service;
import com.utrechtfour.supermarket.dto.PurchaseOrderDTO;
import com.utrechtfour.supermarket.dto.PurchaseOrderItemDTO;

import java.math.BigDecimal;

public interface PurchaseOrderService {

    PurchaseOrderDTO createPurchaseOrder(PurchaseOrderDTO purchaseOrderDto);

    PurchaseOrderDTO updatePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);

    PurchaseOrderDTO updatePurchaseOrder(PurchaseOrderItemDTO purchaseOrderItemDto, Long id);

    PurchaseOrderDTO getPurchaseOrderById(Long id);
}