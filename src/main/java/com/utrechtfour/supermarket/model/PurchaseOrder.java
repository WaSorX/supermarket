package com.utrechtfour.supermarket.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.views.RestViews;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "purchaseOrder")
@Table(name = "purchaseOrder")
public class PurchaseOrder {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({RestViews.PurchaseOrderView.class})
    private long id;
    @OneToOne
    @JsonView({RestViews.PurchaseOrderView.class})
    private Supplier supplier;
    @OneToMany(mappedBy = "purchaseOrder",
                fetch = FetchType.LAZY)
    @JsonView({RestViews.PurchaseOrderView.class})
    private Set<PurchaseOrderItem> purchaseOrderItem;
    @UpdateTimestamp
    private Date updateTime;
    @CreationTimestamp
    private Date creationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setPoItems(Set<PurchaseOrderItem> purchaseOrderItems) {
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
}