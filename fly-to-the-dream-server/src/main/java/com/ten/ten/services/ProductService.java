package com.ten.ten.services;

import com.ten.ten.commons.ResponseResult;
import com.ten.ten.entities.ProductEntity;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public interface ProductService {

    ResponseEntity<ResponseResult> getAllProducts();

    ResponseEntity<ResponseResult> addNewProduct(ProductEntity product);

    void deleteProduct(String id);
}
