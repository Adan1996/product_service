package com.example.api.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.api.models.entities.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long>{
    
}
