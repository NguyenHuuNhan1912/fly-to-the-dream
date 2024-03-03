package com.ten.ten.controllers;

import com.ten.ten.commons.ResponseResult;
import com.ten.ten.constants.CodeResponseConstant;
import com.ten.ten.constants.MessageResponseConstant;
import com.ten.ten.dtos.DeleteProductDto;
import com.ten.ten.entities.ProductEntity;
import com.ten.ten.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ResponseResult responseResult;

    @GetMapping("/products")
    public ResponseEntity<ResponseResult> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/addNewProduct")
    public ResponseEntity<ResponseResult> addNewProduct(@RequestBody ProductEntity product) {
        return productService.addNewProduct(product);
    }

    @PostMapping("/delete-product")
    public ResponseEntity<ResponseResult> deleteProduct(@RequestBody DeleteProductDto deleteProductDto) {
        try {
            productService.deleteProduct(deleteProductDto.getId());
            return responseResult.
                    ResponseResult(
                            HttpStatus.OK,
                            200,
                            null,
                            "Success !"
                    );
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
        }
        return responseResult.
                ResponseResult(
                        HttpStatus.OK,
                        500,
                        null,
                        "Fail !"
                );
    }

}
