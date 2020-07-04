package com.utrechtfour.supermarket.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.service.ProductService;
import com.utrechtfour.supermarket.views.RestViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @JsonView({RestViews.ProductView.class})
    public Optional<Product> getProductById (@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct (@RequestBody @Valid Product product){
         productService.createProduct(product);
    }

    @PutMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@RequestBody @Valid Product product){

        return productService.updateProduct(product);
    }
}