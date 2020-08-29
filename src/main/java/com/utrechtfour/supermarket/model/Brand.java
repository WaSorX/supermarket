package com.utrechtfour.supermarket.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.views.RestViews;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, insertable = false, name = "id")
    @JsonView({RestViews.ProductView.class, RestViews.BrandView.class})
    private Long id;

    @JsonView({RestViews.ProductView.class,RestViews.BrandView.class})
    @NotNull
    private String name;

    @OneToMany(mappedBy = "brand")
    @JsonView({RestViews.BrandView.class})
    private Set<Product> products = new HashSet<Product>();




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Product> getProduct() {
        return products;
    }

    public void setProduct(Set<Product> products) {
        this.products = products;
    }

    public void addProduct (Product product){
        products.add(product);
    }
}
