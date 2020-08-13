package com.utrechtfour.supermarket;


import com.utrechtfour.supermarket.model.Brand;
import com.utrechtfour.supermarket.model.Category;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.Supplier;
import com.utrechtfour.supermarket.repository.BrandRepository;
import com.utrechtfour.supermarket.repository.CategoryRepository;
import com.utrechtfour.supermarket.repository.ProductRepository;
import com.utrechtfour.supermarket.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.HashSet;

@Component
public class DataInit implements ApplicationRunner {

    private ProductRepository productRepository;
    private BrandRepository brandRepository;
    private SupplierRepository supplierRepository;
    private CategoryRepository categoryRepository;


    @Autowired
    public DataInit(ProductRepository productRepository, BrandRepository brandRepository, SupplierRepository supplierRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception{

    }
}
