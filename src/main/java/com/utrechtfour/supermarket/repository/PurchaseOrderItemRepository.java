package com.utrechtfour.supermarket.repository;

import com.utrechtfour.supermarket.model.PurchaseOrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderItemRepository extends CrudRepository<PurchaseOrderItem,Long> {
    List<PurchaseOrderItem> findPurchaseOrderItemsByPurchaseOrderId(Long purchaseOrderId);
}
