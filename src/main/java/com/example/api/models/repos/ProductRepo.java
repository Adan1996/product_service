package com.example.api.models.repos;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.api.models.entities.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {
    
    public List<Product> findByNamaContains(String nama);

    @Query("SELECT p FROM Product p WHERE p.nama LIKE %:nama%")
    public List<Product> getProductByNameLike(@PathParam("nama") String nama);
}
