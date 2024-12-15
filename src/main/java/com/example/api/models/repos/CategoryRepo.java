package com.example.api.models.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.api.models.entities.Category;

public interface CategoryRepo extends PagingAndSortingRepository<Category, Long>{
    
    Page<Category> findByNameContains(String nama, Pageable pageable);
}
