package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.PurchaseOrderItem;
import com.utrechtfour.supermarket.repository.PurchaseOrderItemRepository;
import com.utrechtfour.supermarket.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PurchaseOrderItemServiceImpl implements PurchaseOrderItemService{

    @Autowired
    private PurchaseOrderItemRepository purchaseOrderItemRepository;
    @Autowired
    private ProductService productService;


    @Transactional
    @Override
    public Optional<PurchaseOrderItem> getPurchaseOrderById(long id) {
        return purchaseOrderItemRepository.findById(id);
    }

    @Transactional
    @Override
    public PurchaseOrderItem createPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {

              return purchaseOrderItemRepository.save(purchaseOrderItem);
    }

    @Transactional
    @Override
    public PurchaseOrderItem createAndUpdatePurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
        return purchaseOrderItemRepository.save(purchaseOrderItem);
    }

    @Transactional
    @Override
    public void removePurchaseOrderItemById(long id) {
        purchaseOrderItemRepository.deleteById(id);
    }
}
