package com.utrechtfour.supermarket.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.views.RestViews;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({RestViews.ProductView.class})
    private Long id;

    @JsonView({RestViews.ProductView.class,RestViews.CategoryView.class})
    @NotNull
    @Size(min = 0, max = 50)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<Product>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
