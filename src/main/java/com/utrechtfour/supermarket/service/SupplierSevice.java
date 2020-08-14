package com.utrechtfour.supermarket.service;

import com.utrechtfour.supermarket.model.Category;
import com.utrechtfour.supermarket.model.Supplier;
import com.utrechtfour.supermarket.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.Map;
import java.util.Optional;

@Service
public class SupplierSevice {

    @Autowired
    SupplierRepository repository;

    @Autowired
    EntityManager entityManager;

    @Transactional
    public Optional<Supplier> getSupplierById (Long id) {
        return repository.findById(id);
    }


    @Transactional
    public Supplier createSupplier(Supplier supplier) {
        return repository.save(supplier);
    }

    @Transactional
    public Supplier patchSupplier(Map<String, Object> updates, Long id) throws ValidationException {
        if(!repository.findById(id).isPresent()){
            throw new javax.validation.ValidationException("Cannot find Supplier with an id of " + id);
        }
        Supplier supplier = repository.findById(id).get();


        if (updates.containsKey("name")){
            supplier.setName(String.valueOf(updates.get("name")));
        }

        if (updates.containsKey("id")){
            throw new ValidationException("Id is automatically created by the database, please do the request again without providing an id ");
        }


        repository.save(supplier);

        return supplier;
    }

    @Transactional
    public Supplier updateSupplier(Supplier newSupplier, Long id) {

        Supplier supplier = repository.findById(id).get();

        if (newSupplier.getName() == null){
            throw new ValidationException("Please provide a name");
        }

        supplier.setName(newSupplier.getName());

        return repository.save(supplier);
    }





}
