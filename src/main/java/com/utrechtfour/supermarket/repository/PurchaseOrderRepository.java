package com.utrechtfour.supermarket.repository;

import com.utrechtfour.supermarket.model.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder,Long> {
}
