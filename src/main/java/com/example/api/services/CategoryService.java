package com.example.api.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.api.models.entities.Category;
import com.example.api.models.repos.CategoryRepo;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepo categoryRepo;

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public Category findOne(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if(!category.isPresent()) {
            return null;
        }
        return category.get();
    }

    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    public Iterable<Category> findByNameContains(String nama, Pageable pageable) {
        return categoryRepo.findByNameContains(nama, pageable);
    }

    public void removeOne(Long id) {
        categoryRepo.deleteById(id);
    }
}
