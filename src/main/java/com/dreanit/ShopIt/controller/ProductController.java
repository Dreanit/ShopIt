package com.dreanit.ShopIt.controller;

import com.dreanit.ShopIt.model.APIResponse;
import com.dreanit.ShopIt.model.Product;
import com.dreanit.ShopIt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse<List<Product>>> getAllProducts() {

        List<Product> data=productService.getAllProducts();
        APIResponse<List<Product>> response=new APIResponse<>(true,data );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> getProductById(@PathVariable UUID id) {
        Product data=productService.getProductById(id);
        APIResponse<Product> response=new APIResponse<>(true,data );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse<Product>> createProduct(@RequestBody Product product) {
        Product data=productService.saveProduct(product);
        APIResponse<Product> response=new APIResponse<>(true,data);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public Product updateProduct(@PathVariable UUID id, @RequestBody Product product) {
//        product.setId(id);

        return productService.saveProduct(product);
    }

    @PostMapping("/delete/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }
}
