package com.utrechtfour.supermarket.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.model.Category;
import com.utrechtfour.supermarket.model.Supplier;
import com.utrechtfour.supermarket.service.SupplierSevice;
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
public class SupplierController {


    @Autowired
    SupplierSevice supplierSevice;


    @GetMapping("/supplier/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.SupplierView.class})
    public ResponseEntity getSupplierById (@PathVariable Long id, HttpServletResponse response){

        if (supplierSevice.getSupplierById(id).isPresent()){
            return new ResponseEntity(supplierSevice.getSupplierById(id), HttpStatus.OK);
        }
        else
            throw new ValidationException("Cannot find Supplier with an id of " + id);
    }

    @Transactional
    @PostMapping("/supplier")
    public ResponseEntity<Supplier> createSupplier (@RequestBody @Valid Supplier supplier){

        if (supplier.getId() != null){
            throw new ValidationException("Id is automatically created by the database, please do the request again without providing an id");
        }

        return new ResponseEntity(supplierSevice.createSupplier(supplier), HttpStatus.CREATED);
    }

    @PatchMapping("/supplier/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.SupplierView.class})
    public ResponseEntity<Supplier> updateSupplier(@RequestBody Map<String,Object> updates, @PathVariable Long id) throws ValidationException {

        try {
            return new ResponseEntity(supplierSevice.patchSupplier(updates, id),HttpStatus.OK);
        } catch (ValidationException e) {
            throw e;
        }
    }

    @PutMapping("/supplier/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.SupplierView.class})
    public ResponseEntity<Category> updateSupplier(@RequestBody @Valid Supplier supplier, @PathVariable Long id){

        return new ResponseEntity(supplierSevice.updateSupplier(supplier, id),HttpStatus.OK);
    }




}
