package com.utrechtfour.supermarket.repository;

import com.utrechtfour.supermarket.model.PurchaseOrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderItemRepository extends CrudRepository<PurchaseOrderItem,Long> {
}
