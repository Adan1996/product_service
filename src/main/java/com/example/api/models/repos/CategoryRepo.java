package com.example.api.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.api.models.entities.Category;

public interface CategoryRepo extends CrudRepository<Category, Long>{
    
}
