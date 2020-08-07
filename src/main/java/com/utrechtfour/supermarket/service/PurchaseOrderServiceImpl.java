package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.dto.PurchaseOrderDTO;
import com.utrechtfour.supermarket.dto.PurchaseOrderItemDTO;
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
    @Autowired
    SupplierSevice supplierSevice;

    @Transactional
    @Override
    public PurchaseOrderDTO createPurchaseOrder(PurchaseOrderDTO purchaseOrderDto) {

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setSupplier(supplierSevice.getSupplierById(purchaseOrderDto.getSupplierId()).get());
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        Set<PurchaseOrderItem> purchaseOrderItems = new HashSet<>();
        for(PurchaseOrderItemDTO poItemDto: purchaseOrderDto.getPurchaseOrderItemDto()) {
            PurchaseOrderItem poItem = new PurchaseOrderItem();
            poItem.setProduct(productService.getProductById(poItemDto.getProductId()).get());
            poItem.setPurchasePrice(poItemDto.getPurchasePrice());
            System.out.println("quantity dto = " + poItemDto.getQuantity());
            poItem.setQuantity(poItemDto.getQuantity());
            System.out.println("quantity = " + poItem.getQuantity());
            poItem.setPurchaseOrder(purchaseOrder);
            poItem = purchaseOrderItemService.createPurchaseOrderItem(poItem);
            purchaseOrderItems.add(poItem);
        }

        purchaseOrder = addPurchaseOrderItemsToOrder(purchaseOrderItems, purchaseOrder);
        purchaseOrderDto.setPurchaseOrderId(purchaseOrder.getId());

        return purchaseOrderDto.convertToPurchaseOrderDto(purchaseOrder);
    }

    @Transactional
    @Override
    public void updatePurchaseOrder(PurchaseOrderItemDTO purchaseOrderItemDto, Long purchaseOrderId) {

        PurchaseOrder purchaseOrderdb = purchaseOrderRepository.findById(purchaseOrderId).get();
        Product product  = productService.getProductById(purchaseOrderItemDto.getProductId()).get();

        PurchaseOrderItem newItem = new PurchaseOrderItem();
        newItem.setProduct(product);
        newItem.setPurchaseOrder(purchaseOrderdb);
        newItem.setQuantity(purchaseOrderItemDto.getQuantity());
        newItem.setPurchasePrice(purchaseOrderItemDto.getPurchasePrice());

/*
        purchaseOrderdb = addPurchaseOrderItemToOrder(purchaseOrderItemService.createAndUpdatePurchaseOrderItem(newItem),purchaseOrderdb);
        PurchaseOrderDTO purchaseOrderDto = new PurchaseOrderDTO();
        purchaseOrderDto.setPurchaseOrderId(purchaseOrderdb.getId());
        PurchaseOrderItemDTO poItemsDtos = new HashSet<>();
        for (PurchaseOrderItem poItem: purchaseOrderdb.getPurchaseOrderItems()){
            PurchaseOrderItemDTO poItemDto = new PurchaseOrderItemDTO();
            poItemDto.setPurchaseOrderItemId(poItem.getId());
            poItemDto.setProductId(poItem.getProduct().getId());
            poItemDto.setPurchasePrice(poItem.getPurchasePrice());
            poItemDto.setQuantity(poItem.getQuantity());
            poItemsDtos.add(poItemDto);
        }
        purchaseOrderDto.setPurchaseOrderItemDtos(poItemsDtos);
        return purchaseOrderDto;
  */
    }

    @Override
    public PurchaseOrderDTO getPurchaseOrderById(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).get();
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setPurchaseOrderId(purchaseOrder.getId());
        purchaseOrderDTO.setSupplierId(purchaseOrder.getSupplier().getId());
        return purchaseOrderDTO;

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
    public PurchaseOrder addPurchaseOrderItemsToOrder(Set<PurchaseOrderItem> items, PurchaseOrder order){

        order.setPoItems(items);
        return purchaseOrderRepository.save(order);
    }


}