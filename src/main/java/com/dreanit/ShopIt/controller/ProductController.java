package com.dreanit.ShopIt.controller;

import com.dreanit.ShopIt.dto.ProductUpdateDTO;
import com.dreanit.ShopIt.enums.ProductStatus;
import com.dreanit.ShopIt.model.APIResponse;
import com.dreanit.ShopIt.model.entityModel.Product;
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
    @ResponseBody
    public ResponseEntity<APIResponse<List<Product>>> getAllProducts(@RequestParam ProductStatus status) {
    if(status!=null){
        List<Product> data = productService.getAllProductsFromStatus(status);
        APIResponse<List<Product>> response = new APIResponse<>(true, data, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
        List<Product> data = productService.getAllProducts();
        APIResponse<List<Product>> response = new APIResponse<>(true, data, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> getProductById(@PathVariable UUID id) {
        Product data = productService.getProductById(id);
        APIResponse<Product> response = new APIResponse<>(true, data, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse<Product>> createProduct(@RequestBody Product product) {
        Product data = productService.saveProduct(product);
        APIResponse<Product> response = new APIResponse<>(true, data, "Product Created Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<APIResponse<Product>> updateProduct(@PathVariable UUID id, @RequestBody ProductUpdateDTO product) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            APIResponse<Product> response = new APIResponse<>(false, null, "Product not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        if (product.getStatus() != null) {
            existingProduct.status = product.getStatus();
        }
        if (product.getName() != null) {
            existingProduct.name = product.getName();
        }
        if (product.getPrice() != null) {
            existingProduct.price = product.getPrice();
        }

        Product updatedProduct = productService.saveProduct(existingProduct);

        APIResponse<Product> response = new APIResponse<>(true, updatedProduct, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<APIResponse<Void>> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        try {
            APIResponse<Void> productDeletedSuccessfully = new APIResponse<>(true, null, "Product Deleted Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(productDeletedSuccessfully);
        } catch (Exception e) {
            APIResponse<Void> errorResponse = new APIResponse<>(false, null, "Failed to delete product");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }



    //todo:  make product get all active product api


}
