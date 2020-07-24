package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.dto.PurchaseOrderDto;
import com.utrechtfour.supermarket.dto.PurchaseOrderItemDto;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.PurchaseOrder;
import com.utrechtfour.supermarket.model.PurchaseOrderItem;
import com.utrechtfour.supermarket.repository.ProductRepository;
import com.utrechtfour.supermarket.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    PurchaseOrderItemService purchaseOrderItemService;
    @Autowired
    ProductService productService;

    @Transactional
    @Override
    public PurchaseOrderDto createPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(purchaseOrderDto.getPurchaseOrderId());
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        Set<PurchaseOrderItem> purchaseOrderItems = new HashSet<>();
        for (PurchaseOrderItemDto poItemDto : purchaseOrderDto.getPurchaseOrderItemDtos()){

            PurchaseOrderItem poItem = new PurchaseOrderItem();
            poItem.setId(poItemDto.getProductId());
            poItem.setProduct(productService.getProductById(poItemDto.getProductId()).get());
            poItem.setPurchasePrice(poItemDto.getPurchasePrice());
            poItem.setQuantity(poItemDto.getQuantity());

            purchaseOrderItems.add(poItem);
            purchaseOrder = addPurchaseOrderItemToOrder(poItem, purchaseOrder);
        }
        purchaseOrder.setPoItems(purchaseOrderItems);
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        purchaseOrderDto.setPurchaseOrderId(purchaseOrder.getId());
        return purchaseOrderDto;
    }

    @Transactional
    @Override
    public PurchaseOrderDto updatePurchaseOrder(PurchaseOrderItemDto purchaseOrderItemDto, Long purchaseOrderId) {

        PurchaseOrder purchaseOrderdb = getPurchaseOrderById(purchaseOrderId).get();
        Product product  = productService.getProductById(purchaseOrderItemDto.getProductId()).get();

        PurchaseOrderItem newItem = new PurchaseOrderItem();
        newItem.setProduct(product);
        newItem.setPurchaseOrder(purchaseOrderdb);
        newItem.setQuantity(purchaseOrderItemDto.getQuantity());
        newItem.setPurchasePrice(purchaseOrderItemDto.getPurchasePrice());

        purchaseOrderdb = addPurchaseOrderItemToOrder(purchaseOrderItemService.createAndUpdatePurchaseOrderItem(newItem),purchaseOrderdb);

        PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();
        purchaseOrderDto.setPurchaseOrderId(purchaseOrderdb.getId());
        Set<PurchaseOrderItemDto> poItemsDtos = new HashSet<>();
        for (PurchaseOrderItem poItem: purchaseOrderdb.getPoItems()){
            PurchaseOrderItemDto poItemDto = new PurchaseOrderItemDto();
            poItemDto.setPurchaseOrderItemId(poItem.getId());
            poItemDto.setProductId(poItem.getProduct().getId());
            poItemDto.setPurchasePrice(poItem.getPurchasePrice());
            poItemDto.setQuantity(poItem.getQuantity());
            poItemsDtos.add(poItemDto);
        }
        purchaseOrderDto.setPurchaseOrderItemDtos(poItemsDtos);
        return purchaseOrderDto;
    }

    @Transactional
    public Optional<PurchaseOrder> getPurchaseOrderById(long id){
        return purchaseOrderRepository.findById(id);
    }

    @Transactional
    public PurchaseOrder createOrUpdatePurchaseOrder(PurchaseOrder purchaseOrder){
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Transactional
    public void removePurchaseOrder(long id){
        purchaseOrderRepository.deleteById(id);
    }

    @Transactional
    public PurchaseOrder addPurchaseOrderItemToOrder(PurchaseOrderItem item, PurchaseOrder order){
        Set<PurchaseOrderItem> poItems =  order.getPoItems();
        poItems.add(item);
        order.setPoItems(poItems);
        return purchaseOrderRepository.save(order);
    }


}
