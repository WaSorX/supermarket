package com.utrechtfour.supermarket.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.views.RestViews;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity(name = "purchase_order_item")
@Table(name = "purchase_order_item")
public class PurchaseOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
    @NumberFormat(pattern = "000.00")
    @NotNull
    private BigDecimal purchasePrice;
    @Min(1)
    @NotNull
    private Integer quantity;
    @Min(0)
    @NotNull
    private Integer openQuantity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchaseOrder_id")
    private PurchaseOrder purchaseOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOpenQuantity() {
        return openQuantity;
    }

    public void setOpenQuantity(Integer openQuantity) {
        this.openQuantity = openQuantity;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof PurchaseOrderItem)) return false;
        return id!=null &&
                id.equals(((PurchaseOrderItem) o).getId());
    }
    @Override
    public int hashCode(){
        return 999;
    }
}