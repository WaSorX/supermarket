package com.utrechtfour.supermarket.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.model.Brand;
import com.utrechtfour.supermarket.model.Category;
import com.utrechtfour.supermarket.service.BrandService;
import com.utrechtfour.supermarket.service.CategoryService;
import com.utrechtfour.supermarket.views.RestViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Map;

@Controller
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brand/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.BrandView.class})
    public ResponseEntity getBrandById (@PathVariable Long id, HttpServletResponse response){

        if (brandService.getBrandById(id).isPresent()){
            return new ResponseEntity(brandService.getBrandById(id), HttpStatus.OK);
        }
        else
            throw new ValidationException("Cannot find Brand with an id of " + id);
    }

    @Transactional
    @PostMapping("/brand")
    @JsonView({RestViews.BrandView.class})
    public ResponseEntity<Brand> createBrand (@RequestBody @Valid Brand brand) throws Throwable {

        if (brand.getId() != null){
            throw new ValidationException("Id is automatically created by the database, please do the request again without providing an id");
        }
        return new ResponseEntity(brandService.createBrand(brand),HttpStatus.CREATED);
    }

    @PatchMapping("/brand/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.BrandView.class})
    public ResponseEntity<Brand> updateBrand(@RequestBody Map<String,Object> updates, @PathVariable Long id) throws ValidationException {

        try {
            return new ResponseEntity(brandService.patchBrand(updates, id),HttpStatus.OK);
        } catch (ValidationException e) {
            throw e;
        }
    }

    @PutMapping("/brand/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.BrandView.class})
    public ResponseEntity<Brand> updateCategory(@RequestBody @Valid Brand brand, @PathVariable Long id){

        return new ResponseEntity(brandService.updateBrand(brand, id),HttpStatus.OK);
    }

}
