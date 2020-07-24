package com.utrechtfour.supermarket.controller.rest;


import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.dto.PurchaseOrderDto;
import com.utrechtfour.supermarket.dto.PurchaseOrderItemDto;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.PurchaseOrder;
import com.utrechtfour.supermarket.model.PurchaseOrderItem;
import com.utrechtfour.supermarket.service.PurchaseOrderItemService;
import com.utrechtfour.supermarket.service.PurchaseOrderService;
import com.utrechtfour.supermarket.views.RestViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;



    @PostMapping("/purchaseOrder")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PurchaseOrderDto> createPurchaseOrder(@RequestBody @Valid PurchaseOrderDto purchaseOrderDto){
        if(purchaseOrderDto.getPurchaseOrderId() != null){
            throw new ValidationException("Id is automatically created by the database, please do the request again without providing an id");
        } else {
            return new ResponseEntity<PurchaseOrderDto>(purchaseOrderService.createPurchaseOrder(purchaseOrderDto),HttpStatus.CREATED);
        }
    }

    @PutMapping("/purchaseOrder/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseOrderDto updatePurchaseOrder(@RequestBody PurchaseOrderItemDto purchaseOrderItemDto, @PathVariable Long id){
        return purchaseOrderService.updatePurchaseOrder(purchaseOrderItemDto, id);
    }
}
