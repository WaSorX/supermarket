package com.utrechtfour.supermarket.repository;

import com.utrechtfour.supermarket.model.StockItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepository extends CrudRepository<StockItem, Long> {
}
