package com.example.api.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.models.entities.Product;
import com.example.api.models.entities.Supplier;
import com.example.api.models.repos.ProductRepo;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;

    // method ini berfungsi untuk meng-create atau meng-update
    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Product findOne(Long id) {
        Optional<Product> temp = productRepo.findById(id);
        if(!temp.isPresent()) {
            return null;
        }
        return productRepo.findById(id).get();
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public void removeOne(Long id) {
        productRepo.deleteById(id);
    }

    // method ini berasal dari interface ProductRepo yang dibuat secara manual
    public List<Product> findByName(String nama) {
        return productRepo.findByNamaContains(nama);
    }

    public List<Product> getProductByNameLike(String nama) {
        return productRepo.getProductByNameLike(nama);
    }

    public void addSupplier(Supplier supplier, Long productId) {
        Product product = findOne(productId);
        if (product == null) {
            throw new RuntimeException("Product ID "+ productId +" not found");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }
}
