package com.utrechtfour.supermarket.repository;

import com.utrechtfour.supermarket.model.StockItem;
import org.springframework.data.repository.CrudRepository;

public interface StockItemRepository extends CrudRepository<StockItem, Long> {
}
