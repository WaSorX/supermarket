package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.dto.PurchaseOrderDTO;
import com.utrechtfour.supermarket.dto.PurchaseOrderItemDTO;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.PurchaseOrder;
import com.utrechtfour.supermarket.model.PurchaseOrderItem;
import com.utrechtfour.supermarket.model.PurchaseOrderStatus;
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
        purchaseOrder.setPurchaseOrderStatus(PurchaseOrderStatus.OPEN);
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
                poItem.setOpenQuantity(purchaseOrderItemDTO.getQuantity());
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
    public PurchaseOrder updatePurchaseOrder(PurchaseOrder purchaseOrder) throws NoSuchElementException{
        if(!purchaseOrderRepository.findById(purchaseOrder.getId()).isPresent()){
            throw new NoSuchElementException("Purchase order with id " + purchaseOrder.getId() + " does not exist");
        }

        return purchaseOrderRepository.save(purchaseOrder);
    }


    @Transactional
    public PurchaseOrderDTO addToPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) throws NoSuchElementException{
        PurchaseOrder purchaseOrderDb = purchaseOrderRepository.findById(purchaseOrderDTO.getPurchaseOrderId()).get();
        purchaseOrderDb.setPurchaseOrderStatus(PurchaseOrderStatus.OPEN);
        for (PurchaseOrderItemDTO purchaseOrderItemDTO: purchaseOrderDTO.getPurchaseOrderItemDto()){
            Product product = productService.getProductById(purchaseOrderItemDTO.getProductId()).get();
            if(!product.getSuppliers().contains(purchaseOrderDb.getSupplier())){
                throw new NoSuchElementException("supplier with id = " + purchaseOrderDb.getSupplier().getId() + " does not have product " + product.getId());
            } else {
                PurchaseOrderItem newItem = new PurchaseOrderItem();
                newItem.setProduct(product);
                newItem.setPurchaseOrder(purchaseOrderDb);
                newItem.setQuantity(purchaseOrderItemDTO.getQuantity());
                newItem.setOpenQuantity(purchaseOrderItemDTO.getQuantity());
                newItem.setPurchasePrice(purchaseOrderItemDTO.getPurchasePrice());
                newItem = purchaseOrderItemService.createAndUpdatePurchaseOrderItem(newItem);
                purchaseOrderDb = addPurchaseOrderItemsToOrder(newItem, purchaseOrderDb);
            }

        }
        return PurchaseOrderDTO.convertToPurchaseOrderDto(purchaseOrderDb);
    }

    @Transactional
    @Override
    public PurchaseOrderDTO addToPurchaseOrder(PurchaseOrderItemDTO purchaseOrderItemDto, Long purchaseOrderId) throws NoSuchElementException {

        PurchaseOrder purchaseOrderDb = purchaseOrderRepository.findById(purchaseOrderId).get();
        purchaseOrderDb.setPurchaseOrderStatus(PurchaseOrderStatus.OPEN);
        Product product  = productService.getProductById(purchaseOrderItemDto.getProductId()).get();
        if(!product.getSuppliers().contains(purchaseOrderDb.getSupplier())){
            throw new NoSuchElementException("supplier with id = " + purchaseOrderDb.getSupplier().getId() + " does not have product " + product.getId());
        } else {

            PurchaseOrderItem newItem = new PurchaseOrderItem();
            newItem.setProduct(product);
            newItem.setPurchaseOrder(purchaseOrderDb);
            newItem.setQuantity(purchaseOrderItemDto.getQuantity());
            newItem.setOpenQuantity(purchaseOrderItemDto.getQuantity());
            newItem.setPurchasePrice(purchaseOrderItemDto.getPurchasePrice());
            newItem = purchaseOrderItemService.createAndUpdatePurchaseOrderItem(newItem);
            purchaseOrderDb = addPurchaseOrderItemsToOrder(newItem, purchaseOrderDb);
        }
        return PurchaseOrderDTO.convertToPurchaseOrderDto(purchaseOrderDb);
    }

    @Override
    public PurchaseOrderDTO getPurchaseOrderDTOById(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).get();
        return PurchaseOrderDTO.convertToPurchaseOrderDto(purchaseOrder);
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) throws NoSuchElementException{
        if(purchaseOrderRepository.findById(id).isEmpty()){
            throw new NoSuchElementException("Purchase order with id " + id + " doesn't exist");
        }

        return purchaseOrderRepository.findById(id).get();
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

        order.setPurchaseOrderItems(items);
        return purchaseOrderRepository.save(order);
    }

    public PurchaseOrder addPurchaseOrderItemsToOrder(PurchaseOrderItem item, PurchaseOrder order){
        order.getPurchaseOrderItems().add(item);
        return purchaseOrderRepository.save(order);
    }


}