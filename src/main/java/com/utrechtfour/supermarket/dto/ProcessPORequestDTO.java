package com.utrechtfour.supermarket.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.views.RestViews;

public class ProcessPORequestDTO {


    @JsonView(RestViews.ProcessPORequestView.class)
    @JsonProperty("id")
    private Long purchaseOrderId;

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }
}
