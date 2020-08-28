package com.utrechtfour.supermarket.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.views.RestViews;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class
Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, insertable = false)
    @JsonView({RestViews.ProductView.class, RestViews.SupplierView.class})
    private Long id;
    @JsonView(RestViews.SupplierView.class)
    private String name;
    @ManyToMany( mappedBy = "suppliers")
    private Set<Product> products;
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PurchaseOrder> purchaseOrder;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PurchaseOrder> getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(Set<PurchaseOrder> purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}


