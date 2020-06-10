package org.smarttechie.controller;

import org.smarttechie.model.Product;
import org.smarttechie.repository.ProductRepository;
import org.smarttechie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * This endpoint allows to create a product.
     * @param product - to create
     * @return - the created product
     */
    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> createProduct(@RequestBody Product product){
        return productService.save(product);
    }

    /**
     * This endpoint gives all the products
     * @return - the list of products available
     */
    @GetMapping("/products")
    public Flux<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    /**
     * This endpoint allows to delete a product
     * @param id - to delete
     * @return
     */
    @DeleteMapping("/product/{id}")
    public Mono<Void> deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    /**
     * This endpoint allows to update a product
     * @param product - to update
     * @return - the updated product
     */
    @PutMapping("product/{id}")
    public Mono<ResponseEntity<Product>> updateProduct(@RequestBody Product product){
        return productService.update(product);
    }
}
