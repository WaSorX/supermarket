package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.dto.PurchaseOrderDTO;
import com.utrechtfour.supermarket.dto.PurchaseOrderItemDTO;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.PurchaseOrder;
import com.utrechtfour.supermarket.model.PurchaseOrderItem;
import com.utrechtfour.supermarket.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public PurchaseOrderDTO createPurchaseOrder(PurchaseOrderDTO purchaseOrderDto) throws NoSuchElementException{

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setSupplier(supplierSevice.getSupplierById(purchaseOrderDto.getSupplierId()).get());
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        Set<PurchaseOrderItem> purchaseOrderItems = new HashSet<>();
        for(PurchaseOrderItemDTO purchaseOrderItemDTO: purchaseOrderDto.getPurchaseOrderItemDto()) {
           Product product = productService.getProductById(purchaseOrderItemDTO.getProductId()).get();
            if(!product.getSuppliers().contains(purchaseOrder.getSupplier())) {
                throw new NoSuchElementException("supplier with id = " + purchaseOrder.getSupplier().getId() + " does not have product " + product.getId());
            } else {
                PurchaseOrderItem poItem = new PurchaseOrderItem();
                poItem.setProduct(productService.getProductById(purchaseOrderItemDTO.getProductId()).get());
                poItem.setPurchasePrice(purchaseOrderItemDTO.getPurchasePrice());
                poItem.setQuantity(purchaseOrderItemDTO.getQuantity());
                poItem.setPurchaseOrder(purchaseOrder);
                poItem = purchaseOrderItemService.createPurchaseOrderItem(poItem);
                purchaseOrderItems.add(poItem);
            }
        }

        purchaseOrder = addPurchaseOrderItemsToOrder(purchaseOrderItems, purchaseOrder);
        purchaseOrderDto.setPurchaseOrderId(purchaseOrder.getId());

        return PurchaseOrderDTO.convertToPurchaseOrderDto(purchaseOrder);
    }

    @Transactional
    @Override
    public PurchaseOrderDTO updatePurchaseOrder(PurchaseOrderItemDTO purchaseOrderItemDto, Long purchaseOrderId) throws NoSuchElementException {

        PurchaseOrder purchaseOrderdb = purchaseOrderRepository.findById(purchaseOrderId).get();
        Product product  = productService.getProductById(purchaseOrderItemDto.getProductId()).get();
        if(!product.getSuppliers().contains(purchaseOrderdb.getSupplier())){
            throw new NoSuchElementException("supplier with id = " + purchaseOrderdb.getSupplier().getId() + " does not have product " + product.getId());
        } else {

            PurchaseOrderItem newItem = new PurchaseOrderItem();
            newItem.setProduct(product);
            newItem.setPurchaseOrder(purchaseOrderdb);
            newItem.setQuantity(purchaseOrderItemDto.getQuantity());
            newItem.setPurchasePrice(purchaseOrderItemDto.getPurchasePrice());
            newItem = purchaseOrderItemService.createAndUpdatePurchaseOrderItem(newItem);
            purchaseOrderdb = addPurchaseOrderItemsToOrder(newItem, purchaseOrderdb);
        }
        return PurchaseOrderDTO.convertToPurchaseOrderDto(purchaseOrderdb);
    }

    @Override
    public PurchaseOrderDTO getPurchaseOrderById(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).get();
        return PurchaseOrderDTO.convertToPurchaseOrderDto(purchaseOrder);
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

    public PurchaseOrder addPurchaseOrderItemsToOrder(PurchaseOrderItem item, PurchaseOrder order){
        order.getPurchaseOrderItems().add(item);
        return purchaseOrderRepository.save(order);
    }


}