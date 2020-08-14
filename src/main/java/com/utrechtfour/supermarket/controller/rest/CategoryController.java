package com.utrechtfour.supermarket.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.model.Category;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.service.CategoryService;
import com.utrechtfour.supermarket.views.RestViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Map;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.CategoryView.class})
    public ResponseEntity getCategoryById (@PathVariable Long id, HttpServletResponse response){

        if (categoryService.getCategoryById(id).isPresent()){
            return new ResponseEntity(categoryService.getCategoryById(id), HttpStatus.OK);
        }
        else
            throw new ValidationException("Cannot find Category with an id of " + id);
    }

    @Transactional
    @PostMapping("/category")
    @JsonView({RestViews.CategoryView.class})
    public ResponseEntity<Category> createCategory (@RequestBody @Valid Category category) throws Throwable {

        if (category.getId() != null){
            throw new ValidationException("Id is automatically created by the database, please do the request again without providing an id");
        }
        return new ResponseEntity(categoryService.createCategory(category),HttpStatus.CREATED);
    }

    @PatchMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.CategoryView.class})
    public ResponseEntity<Category> updateCategory(@RequestBody Map<String,Object> updates, @PathVariable Long id) throws ValidationException {

        try {
            return new ResponseEntity(categoryService.patchCategory(updates, id),HttpStatus.OK);
        } catch (ValidationException e) {
           throw e;
        }
    }

    @PutMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.CategoryView.class})
    public ResponseEntity<Category> updateCategory(@RequestBody @Valid Category category, @PathVariable Long id){

        return new ResponseEntity(categoryService.updateCategory(category, id),HttpStatus.OK);
    }


}
