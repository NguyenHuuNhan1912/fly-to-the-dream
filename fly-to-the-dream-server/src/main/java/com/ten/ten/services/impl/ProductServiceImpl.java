package com.ten.ten.services.impl;

import com.ten.ten.commons.ResponseResult;
import com.ten.ten.entities.ProductEntity;
import com.ten.ten.repositories.ProductRepository;
import com.ten.ten.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public ResponseEntity<ResponseResult> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseResult(200, productRepository.getAllProducts(), "SUCCESS"));
    }
    @Override
    public ResponseEntity<ResponseResult> addNewProduct(ProductEntity product) {
        ProductEntity newProduct = new ProductEntity();
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        productRepository.save(newProduct);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseResult(200, productRepository.getAllProducts(), "SUCCESS"));
    }


    @Transactional
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
