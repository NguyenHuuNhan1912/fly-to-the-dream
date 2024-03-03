package com.ten.ten.repositories;

import com.ten.ten.commons.ResponseResult;
import com.ten.ten.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "SELECT * FROM products", nativeQuery = true)
    List<ProductEntity> getAllProducts();

   void deleteById(String id);
}
