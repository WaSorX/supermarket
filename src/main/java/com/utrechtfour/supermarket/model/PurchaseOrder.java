package com.utrechtfour.supermarket.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.views.RestViews;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "purchase_order")
@Entity(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({RestViews.PurchaseOrderView.class})
    private Long id;
    @OneToOne
    @JsonView({RestViews.PurchaseOrderView.class})
    private Supplier supplier;
    @OneToMany(mappedBy = "purchaseOrder",
            fetch = FetchType.LAZY)
    @JsonView({RestViews.PurchaseOrderView.class})
    private Set<PurchaseOrderItem> purchaseOrderItem;
    @JsonView({RestViews.PurchaseOrderView.class})
    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus purchaseOrderStatus;
    @UpdateTimestamp
    private Date updateTime;
    @CreationTimestamp
    private Date creationTime;
    @OneToMany(mappedBy = "purchaseOrder",
            fetch = FetchType.LAZY)
    private Set<StockItem> stockItem;

    public Set<PurchaseOrderItem> getPurchaseOrderItem() {
        return purchaseOrderItem;
    }

    public void setPurchaseOrderItem(Set<PurchaseOrderItem> purchaseOrderItem) {
        this.purchaseOrderItem = purchaseOrderItem;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<PurchaseOrderItem> getPurchaseOrderItems() {
        return purchaseOrderItem;
    }

    public void setPurchaseOrderItems(Set<PurchaseOrderItem> purchaseOrderItems) {
        this.purchaseOrderItem = purchaseOrderItems;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }


    public PurchaseOrderStatus getPurchaseOrderStatus() {
        return purchaseOrderStatus;
    }

    public void setPurchaseOrderStatus(PurchaseOrderStatus purchaseOrderStatus) {
        this.purchaseOrderStatus = purchaseOrderStatus;
    }

    public Set<StockItem> getStockItem() {
        return stockItem;
    }

    public void setStockItem(Set<StockItem> stockItem) {
        this.stockItem = stockItem;
    }

    public void addPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem){
        this.purchaseOrderItem.add(purchaseOrderItem);
        purchaseOrderItem.setPurchaseOrder(this);
    }

    public void removePurchaseOrderItem(PurchaseOrderItem purchaseOrderItem){
        this.purchaseOrderItem.remove(purchaseOrderItem);
        purchaseOrderItem.setPurchaseOrder(null);
    }
}



