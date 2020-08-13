package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.Brand;
import com.utrechtfour.supermarket.model.Category;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.model.Supplier;
import com.utrechtfour.supermarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyNameException;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;

    @Transactional
    public Optional<Category> getCategoryById (Long id){
        return repository.findById(id);

    }

    @Transactional
    public Category createCategory(Category category) throws InvalidConfigurationPropertyValueException {

        repository.save(category);
        return repository.findById(category.getId()).get();
    }

    @Transactional
    public Category patchCategory(Map<String, Object> updates, Long id) throws ValidationException {
        if(!repository.findById(id).isPresent()){
            throw new javax.validation.ValidationException("Cannot find Category with an id of " + id);
        }
        Category category = repository.findById(id).get();


        if (updates.containsKey("name")){
            category.setName(String.valueOf(updates.get("name")));
        }

        if (updates.containsKey("id")){
            throw new ValidationException("Id is automatically created by the database, please do the request again without providing an id ");
        }


        repository.save(category);

        return category;
    }

    @Transactional
    public Category updateCategory(Category newCategory, Long id) {

        Category category = repository.findById(id).get();

        if (newCategory.getName() == null){
            throw new ValidationException("Please provide a name");
        }

        category.setName(newCategory.getName());

        return repository.save(category);
    }



}
