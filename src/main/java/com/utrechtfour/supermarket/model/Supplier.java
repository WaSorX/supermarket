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
    @JsonView({RestViews.ProductView.class})
    private Long id;
    private String name;
    @ManyToMany( mappedBy = "suppliers")
    private Set<Product> products;
    @OneToOne(mappedBy = "supplier", cascade = CascadeType.ALL)
    private PurchaseOrder purchaseOrder;

    @JsonView({RestViews.ProductView.class})
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


}


