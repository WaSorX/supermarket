package com.utrechtfour.supermarket.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.model.PurchaseOrder;
import com.utrechtfour.supermarket.views.RestViews;


import java.util.Set;

public class PurchaseOrderDto {

    @JsonView({RestViews.PurchaseOrderView.class})
    private Long PurchaseOrderId;
    @JsonView({RestViews.PurchaseOrderView.class})
    private Set<PurchaseOrderItemDto> purchaseOrderItemDtos;

    public Long getPurchaseOrderId() {
        return PurchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        PurchaseOrderId = purchaseOrderId;
    }

    public Set<PurchaseOrderItemDto> getPurchaseOrderItemDtos() {
        return purchaseOrderItemDtos;
    }

    public void setPurchaseOrderItemDtos(Set<PurchaseOrderItemDto> purchaseOrderItemDtos) {
        this.purchaseOrderItemDtos = purchaseOrderItemDtos;
    }


}
