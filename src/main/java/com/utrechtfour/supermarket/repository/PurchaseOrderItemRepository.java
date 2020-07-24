package com.utrechtfour.supermarket.repository;

import com.utrechtfour.supermarket.model.PurchaseOrderItem;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseOrderItemRepository extends CrudRepository<PurchaseOrderItem,Long> {
}
