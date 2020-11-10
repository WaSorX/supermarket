package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.PurchaseOrder;
import com.utrechtfour.supermarket.model.PurchaseOrderItem;
import com.utrechtfour.supermarket.model.PurchaseOrderStatus;
import com.utrechtfour.supermarket.model.StockItem;
import com.utrechtfour.supermarket.repository.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class StockItemServiceImpl implements StockItemService{

    @Autowired
    StockItemRepository stockItemRepository;
    @Autowired
    PurchaseOrderService purchaseOrderService;
    @Autowired
    PurchaseOrderItemService purchaseOrderItemService;


    @Override
    public StockItem createStockItem(StockItem stockItem) {
        return stockItemRepository.save(stockItem);
    }

    @Override
    public StockItem updateStockItem(StockItem stockItem) {

        if (stockItemRepository.findById(stockItem.getId()).isEmpty()) {
            throw new NoSuchElementException("This stock item with id " + stockItem.getId() + " does not exist.");
        }
        return stockItemRepository.save(stockItem);
    }

    @Transactional
    @Override
    public void processPurchaseOrder(Long purchaseOrderId) {

        PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderById(purchaseOrderId);

        if(!purchaseOrder.getPurchaseOrderStatus().equals(PurchaseOrderStatus.OPEN)){
            throw new ValidationException("Purchase order with id " + purchaseOrder.getId() + " is not open");
        }
        Set<PurchaseOrderItem> updatedPurchaseOrderItems = new HashSet<>();
        Set<StockItem> stockItems = new HashSet<>();
        List<PurchaseOrderItem> purchaseOrderItems = purchaseOrderItemService.findPurchaseOrderItemsByPOId(purchaseOrderId);

        for (PurchaseOrderItem purchaseOrderItem : purchaseOrderItems){
            StockItem stockItem = new StockItem();
            stockItem.setPurchaseOrder(purchaseOrderItem.getPurchaseOrder());
            stockItem.setProduct(purchaseOrderItem.getProduct());
            stockItem.setStock(purchaseOrderItem.getQuantity());
            stockItem.setPurchasePrice(purchaseOrderItem.getPurchasePrice());
            stockItem.setSupplied(purchaseOrderItem.getQuantity());

            stockItems.add(stockItem);
            stockItemRepository.save(stockItem);

            purchaseOrderItem.setOpenQuantity(0);
            purchaseOrderItemService.createAndUpdatePurchaseOrderItem(purchaseOrderItem);
            updatedPurchaseOrderItems.add(purchaseOrderItem);
        }
        purchaseOrder.setPurchaseOrderItems(updatedPurchaseOrderItems);
        purchaseOrder.setPurchaseOrderStatus(PurchaseOrderStatus.FULLY_DELIVERED);
        purchaseOrder.setStockItem(stockItems);
        purchaseOrderService.updatePurchaseOrder(purchaseOrder);

    }

    @Override
    public void removeStockItem(Long id) {
        stockItemRepository.deleteById(id);
    }
}
