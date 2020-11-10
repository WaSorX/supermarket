package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.PurchaseOrderItem;
import com.utrechtfour.supermarket.repository.PurchaseOrderItemRepository;
import com.utrechtfour.supermarket.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderItemServiceImpl implements PurchaseOrderItemService{

    @Autowired
    private PurchaseOrderItemRepository purchaseOrderItemRepository;
    @Autowired
    private ProductService productService;


    @Transactional
    @Override
    public Optional<PurchaseOrderItem> getPurchaseOrderById(Long id) {
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
    public void removePurchaseOrderItemById(Long id) {
        purchaseOrderItemRepository.deleteById(id);
    }

    @Override
    public List<PurchaseOrderItem> findPurchaseOrderItemsByPOId(Long purchaseOrderId) {
        return purchaseOrderItemRepository.findPurchaseOrderItemsByPurchaseOrderId(purchaseOrderId);
    }
}