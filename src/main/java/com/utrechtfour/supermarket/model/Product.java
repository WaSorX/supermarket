package com.utrechtfour.supermarket.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.views.RestViews;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.NumberFormat;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

@Entity
@DynamicUpdate
public class Product {



    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({RestViews.ProductView.class})
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView({RestViews.ProductView.class})
    @Size(min = 13, max = 13)
    private String barcode;

    @NotBlank
    @JsonView({RestViews.ProductView.class})
    private String name;

    @JsonView({RestViews.ProductView.class})
    private String description;

    @CreationTimestamp
    private Date creationTime;
    @UpdateTimestamp
    private Date updateTime;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonView({RestViews.ProductView.class})
    private Category category;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonView({RestViews.ProductView.class})
    private Brand brand;

    @NotNull(message = "Accepted values range: 0-3")
    @Enumerated(EnumType.STRING)
    @JsonView(RestViews.ProductView.class)
    private VatTariff vatTarrif;

    @NotNull(message = "Accepted values range: 0-2")
    @Enumerated(EnumType.STRING)
    @JsonView(RestViews.ProductView.class)
    private Unit unit;

    @NumberFormat(pattern = "000.00")
    @JsonView({RestViews.ProductView.class})
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "product_suppliers", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "supplier_id")})
    @JsonView({RestViews.ProductView.class})
    private Set<Supplier> suppliers = new HashSet<>();

    @ManyToMany (fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.MERGE})
    @JoinTable(name = "purchaseorderitem_product", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "purchaseorderitem_id")})
    private Set<PurchaseOrderItem> purchaseOrderItem;

    public Product() {}

    public Product(@Size(min = 13, max = 13) String barcode, @NotBlank String name, String description, Date creationTime, Date updateTime, @NotNull Category category, @NotNull VatTariff vatTarrif, @NotNull Unit unit, BigDecimal price, @NotNull Brand brand, Set<Supplier> suppliers) {
        this.barcode = barcode;
        this.name = name;
        this.description = description;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.category = category;
        this.vatTarrif = vatTarrif;
        this.unit = unit;
        this.price = price;
        this.brand = brand;
        this.suppliers = suppliers;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        category.addProduct(this);
    }

    public VatTariff getVatTarrif() {
        return vatTarrif;
    }

    public void setVatTarrif(Integer vatTarrif) {
        this.vatTarrif = VatTariff.tariff(vatTarrif);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {

        this.brand = brand;
        brand.addProduct(this);
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public void addSupplier (Supplier supplier){
        this.suppliers.add(supplier);

        if (!supplier.getProducts().contains(this)){
            supplier.addProduct(this);
        }
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = Unit.unit(unit);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PurchaseOrderItem> getPurchaseOrderItem() {
        return purchaseOrderItem;
    }

    public void setPurchaseOrderItem(Set<PurchaseOrderItem> purchaseOrderItem) {
        this.purchaseOrderItem = purchaseOrderItem;
    }
}
