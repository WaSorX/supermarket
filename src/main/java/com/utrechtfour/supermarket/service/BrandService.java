package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.Brand;
import com.utrechtfour.supermarket.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.Map;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    BrandRepository repository;

    @Transactional
    public Optional<Brand> getBrandById (Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Brand createBrand(Brand brand) throws InvalidConfigurationPropertyValueException {

        repository.save(brand);
        return repository.findById(brand.getId()).get();
    }

    @Transactional
    public Brand patchBrand(Map<String, Object> updates, Long id) throws ValidationException {
        if(!repository.findById(id).isPresent()){
            throw new javax.validation.ValidationException("Cannot find Brand with an id of " + id);
        }
        Brand brand = repository.findById(id).get();


        if (updates.containsKey("name")){
            brand.setName(String.valueOf(updates.get("name")));
        }

        if (updates.containsKey("id")){
            throw new ValidationException("Id is automatically created by the database, please do the request again without providing an id ");
        }


        repository.save(brand);

        return brand;
    }

    @Transactional
    public Brand updateBrand(Brand newBrand, Long id) {

        Brand brand = repository.findById(id).get();

        if (newBrand.getName() == null){
            throw new ValidationException("Please provide a name");
        }

        brand.setName(newBrand.getName());

        return repository.save(brand);
    }


}
