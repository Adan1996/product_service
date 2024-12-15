package com.example.api.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.CategoryData;
import com.example.api.dto.ResponseData;
import com.example.api.dto.SearchData;
import com.example.api.models.entities.Category;
import com.example.api.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();

        if(errors.hasErrors()) {
            for(ObjectError error: errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        
        Category category = modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryData categoryData, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();

        if(errors.hasErrors()) {
            for(ObjectError error: errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        
        Category category = modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Category> findAll() {
        return categoryService.findAll();
    }

    // Search data with pagination
    @PostMapping("/search/{size}/{page}")
    public Iterable<Category> findByNameContains(
        @RequestBody SearchData searchData, 
        @PathVariable("size") int size, 
        @PathVariable("page") int page
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryService.findByNameContains(searchData.getSearchKey(), pageable);
    }

    // Search data with pagination and sorting
    @PostMapping("/search/{size}/{page}/{sort}")
    public Iterable<Category> findByNameContains(
        @RequestBody SearchData searchData, 
        @PathVariable("size") int size, 
        @PathVariable("page") int page,
        @PathVariable("sort") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        if(sort.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return categoryService.findByNameContains(searchData.getSearchKey(), pageable);
    }
    
    @GetMapping("/{id}")
    public Category findById(@PathVariable("id") Long id) {
        return categoryService.findOne(id);
    }
}
