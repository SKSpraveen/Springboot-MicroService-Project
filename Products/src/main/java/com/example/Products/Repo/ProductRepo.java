package com.example.Products.Repo;

import com.example.Products.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
    @Query(value = "SELECT * FROM product_entity WHERE product_id=?1", nativeQuery = true)
    ProductEntity getProductById(Integer productId);
}
