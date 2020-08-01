package com.utrechtfour.supermarket.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.model.PurchaseOrder;
import com.utrechtfour.supermarket.views.RestViews;


import java.util.Set;

public class PurchaseOrderDTO {

    @JsonView({RestViews.PurchaseOrderView.class})
    private Long purchaseOrderId;
    @JsonView({RestViews.PurchaseOrderView.class})
    private Long supplierId;
    @JsonView({RestViews.PurchaseOrderView.class})
    private Set<PurchaseOrderItemDTO> purchaseOrderItemDtos;

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

    public Set<PurchaseOrderItemDTO> getPurchaseOrderItemDtos() {
        return purchaseOrderItemDtos;
    }

    public void setPurchaseOrderItemDtos(Set<PurchaseOrderItemDTO> purchaseOrderItemDtos) {
        this.purchaseOrderItemDtos = purchaseOrderItemDtos;
    }


}