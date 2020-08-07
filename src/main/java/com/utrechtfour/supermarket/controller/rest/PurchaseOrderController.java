package com.utrechtfour.supermarket.controller.rest;
import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.dto.PurchaseOrderDTO;
import com.utrechtfour.supermarket.dto.PurchaseOrderItemDTO;
import com.utrechtfour.supermarket.service.PurchaseOrderService;
import com.utrechtfour.supermarket.views.RestViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@RestController
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping("/purchaseOrder")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(@RequestBody @Valid PurchaseOrderDTO purchaseOrderDto){
        if(purchaseOrderDto.getPurchaseOrderId() != null){
            throw new ValidationException("Id is automatically created by the database, please do the request again without providing an id");
        } else {
            return new ResponseEntity<PurchaseOrderDTO>(purchaseOrderService.createPurchaseOrder(purchaseOrderDto),HttpStatus.CREATED);
        }
    }

    @GetMapping("/purchaseOrderDemo")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PurchaseOrderDTO> createPurchaseOrderDemo(){

        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        PurchaseOrderItemDTO purchaseOrderItemDTO = new PurchaseOrderItemDTO();
        Set<PurchaseOrderItemDTO> purchaseOrderItemDTOS = new HashSet<>();
        purchaseOrderDTO.setSupplierId(1L);
        purchaseOrderItemDTO.setProductId(36L);
        purchaseOrderItemDTO.setPurchasePrice(BigDecimal.valueOf(1.00));
        purchaseOrderItemDTO.setQuantity(100);
        purchaseOrderItemDTOS.add(purchaseOrderItemDTO);
        purchaseOrderDTO.setPurchaseOrderItemDto(purchaseOrderItemDTOS);

        return new ResponseEntity(purchaseOrderService.createPurchaseOrder(purchaseOrderDTO),HttpStatus.OK);
    }

    @GetMapping("/purchaseOrder/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.ProductView.class})
    public ResponseEntity getProductById (@PathVariable Long id, HttpServletResponse response){

        if (purchaseOrderService.getPurchaseOrderById(id) != null){
            return new ResponseEntity(purchaseOrderService.getPurchaseOrderById(id), HttpStatus.OK);
        }
        else
            throw new ValidationException("Cannot find Product with an id of " + id);
    }


/*
    @PutMapping("/purchaseOrder/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseOrderDTO updatePurchaseOrder(@RequestBody PurchaseOrderItemDTO purchaseOrderItemDto, @PathVariable Long id){
        return purchaseOrderService.updatePurchaseOrder(purchaseOrderItemDto, id);
    }

 */
}