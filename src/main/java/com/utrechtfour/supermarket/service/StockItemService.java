package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.PurchaseOrder;
import com.utrechtfour.supermarket.model.StockItem;

public interface StockItemService {

    StockItem createStockItem(StockItem stockItem);

    StockItem updateStockItem(StockItem stockItem);

    void processPurchaseOrder(Long purchaseOrderId);

    void removeStockItem(Long id);
}
