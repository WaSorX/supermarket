package com.utrechtfour.supermarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.model.PurchaseOrder;
import com.utrechtfour.supermarket.views.RestViews;


import java.util.Set;

public class PurchaseOrderDTO {

    @JsonView({RestViews.PurchaseOrderView.class})
    @JsonProperty("id")
    private Long purchaseOrderId;
    @JsonView({RestViews.PurchaseOrderView.class})
    @JsonProperty("supplier_id")
    private Long supplierId;
    @JsonView({RestViews.PurchaseOrderView.class})
    @JsonProperty("purchase_order_item")
    private Set<PurchaseOrderItemDTO> purchaseOrderItemDto;

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Set<PurchaseOrderItemDTO> getPurchaseOrderItemDto() {
        return purchaseOrderItemDto;
    }

    public void setPurchaseOrderItemDto(Set<PurchaseOrderItemDTO> purchaseOrderItemDtos) {
        this.purchaseOrderItemDto = purchaseOrderItemDtos;
    }

    public PurchaseOrderDTO convertToPurchaseOrderDto(PurchaseOrder purchaseOrder){
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setPurchaseOrderId(purchaseOrder.getId());
        purchaseOrderDTO.setSupplierId(purchaseOrder.getSupplier().getId());
        purchaseOrderDTO.setPurchaseOrderItemDto(PurchaseOrderItemDTO.convertToDTOSet(purchaseOrder.getPurchaseOrderItems()));
        return purchaseOrderDTO;
    }

}