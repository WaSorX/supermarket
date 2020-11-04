package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.PurchaseOrder;
import com.utrechtfour.supermarket.model.PurchaseOrderItem;
import com.utrechtfour.supermarket.model.PurchaseOrderStatus;
import com.utrechtfour.supermarket.model.StockItem;
import com.utrechtfour.supermarket.repository.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@Transactional
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
            throw new NoSuchElementException("This stock item with id " + stockItem.getId() + " does noet exist.");
        }
        return stockItemRepository.save(stockItem);
    }

    @Override
    public void processPurchaseOrder(Long purchaseOrderId) {

        PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderById(purchaseOrderId);

        if(!purchaseOrder.getPurchaseOrderStatus().equals(PurchaseOrderStatus.OPEN)){
            throw new ValidationException("Purchase order with id " + purchaseOrder.getId() + " is not open");
        }

        for (PurchaseOrderItem purchaseOrderItem : purchaseOrder.getPurchaseOrderItems()){
            StockItem stockItem = new StockItem();
            stockItem.setPurchaseOrder(purchaseOrderItem.getPurchaseOrder());
            stockItem.setProduct(purchaseOrderItem.getProduct());
            stockItem.setStock(purchaseOrderItem.getQuantity());
            stockItem.setPurchasePrice(purchaseOrderItem.getPurchasePrice());
            stockItem.setSupplied(purchaseOrderItem.getQuantity());

            purchaseOrderItem.setOpenQuantity(0);


            stockItemRepository.save(stockItem);
            purchaseOrderItemService.createAndUpdatePurchaseOrderItem(purchaseOrderItem);
        }

        purchaseOrder.setPurchaseOrderStatus(PurchaseOrderStatus.FULLY_DELIVERED);
        purchaseOrderService.updatePurchaseOrder(purchaseOrder);
    }

    @Override
    public void removeStockItem(Long id) {

    }
}
