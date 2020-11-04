package com.utrechtfour.supermarket.service;
import com.utrechtfour.supermarket.dto.PurchaseOrderDTO;
import com.utrechtfour.supermarket.dto.PurchaseOrderItemDTO;
import com.utrechtfour.supermarket.model.PurchaseOrder;

import java.math.BigDecimal;
import java.util.Optional;

public interface PurchaseOrderService {

    PurchaseOrderDTO createPurchaseOrder(PurchaseOrderDTO purchaseOrderDto);

    PurchaseOrder updatePurchaseOrder(PurchaseOrder purchaseOrder);

    PurchaseOrderDTO addToPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);

    PurchaseOrderDTO addToPurchaseOrder(PurchaseOrderItemDTO purchaseOrderItemDto, Long id);

    PurchaseOrderDTO getPurchaseOrderDTOById(Long id);

    PurchaseOrder getPurchaseOrderById(Long id);
}