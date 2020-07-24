package com.utrechtfour.supermarket.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.views.RestViews;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class PurchaseOrderItemDto {

    @JsonView({RestViews.PurchaseOrderView.class})
    private long PurchaseOrderItemId;
    @JsonView({RestViews.PurchaseOrderView.class})
    private long ProductId;
    @JsonView({RestViews.PurchaseOrderView.class})
    @Min(0)
    private int quantity;
    @JsonView({RestViews.PurchaseOrderView.class})
    @NumberFormat(pattern = "000.00")
    private BigDecimal purchasePrice;

    public long getPurchaseOrderItemId() {
        return PurchaseOrderItemId;
    }

    public void setPurchaseOrderItemId(long purchaseOrderItemId) {
        PurchaseOrderItemId = purchaseOrderItemId;
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
