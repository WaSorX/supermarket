package com.utrechtfour.supermarket.controller.rest;
import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.dto.ProcessPORequestDTO;
import com.utrechtfour.supermarket.dto.PurchaseOrderDTO;
import com.utrechtfour.supermarket.dto.PurchaseOrderItemDTO;
import com.utrechtfour.supermarket.service.PurchaseOrderService;
import com.utrechtfour.supermarket.service.StockItemService;
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
    @Autowired
    private StockItemService stockItemService;

    @PostMapping("/purchaseOrder")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(@RequestBody @Valid PurchaseOrderDTO purchaseOrderDto){
        if(purchaseOrderDto.getPurchaseOrderId() != null){
            throw new ValidationException("Id is automatically created by the database, please do the request again without providing an id");
        } else {
            return new ResponseEntity<PurchaseOrderDTO>(purchaseOrderService.createPurchaseOrder(purchaseOrderDto),HttpStatus.CREATED);
        }
    }

    @GetMapping("/purchaseOrder/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.PurchaseOrderView.class})
    public ResponseEntity getPurchaseOrderById (@PathVariable Long id, HttpServletResponse response){

        if (purchaseOrderService.getPurchaseOrderDTOById(id) != null){
            return new ResponseEntity(purchaseOrderService.getPurchaseOrderDTOById(id), HttpStatus.OK);
        }
        else
            throw new ValidationException("Cannot find Purchase Order with an id of " + id);
    }


    @PutMapping("/purchaseOrder/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.PurchaseOrderView.class})
    public ResponseEntity<PurchaseOrderDTO> updatePurchaseOrder(@RequestBody PurchaseOrderItemDTO purchaseOrderItemDto, @PathVariable Long id){
        return new ResponseEntity<PurchaseOrderDTO>(purchaseOrderService.addToPurchaseOrder(purchaseOrderItemDto, id),HttpStatus.OK);
    }

    @PutMapping("/purchaseOrder/")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.PurchaseOrderView.class})
    public ResponseEntity<PurchaseOrderDTO> updatePurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO){
        if (purchaseOrderDTO.getPurchaseOrderId() != null){
         return new ResponseEntity<PurchaseOrderDTO>(purchaseOrderService.addToPurchaseOrder(purchaseOrderDTO),HttpStatus.OK);
        }
        else
            throw new ValidationException("No purchase order id found");

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

        return new ResponseEntity<PurchaseOrderDTO>(purchaseOrderService.createPurchaseOrder(purchaseOrderDTO),HttpStatus.OK);
    }

    @PostMapping("/processPurchaseOrder")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PurchaseOrderDTO> processPurchaseOrder(@RequestBody ProcessPORequestDTO poRequestDTO){
        stockItemService.processPurchaseOrder(poRequestDTO.getPurchaseOrderId());
        return new ResponseEntity<PurchaseOrderDTO>(purchaseOrderService.getPurchaseOrderDTOById(poRequestDTO.getPurchaseOrderId()),HttpStatus.OK);
    }
}