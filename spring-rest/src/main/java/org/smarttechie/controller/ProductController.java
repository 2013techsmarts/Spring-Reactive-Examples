package org.smarttechie.controller;

import org.smarttechie.model.Product;
import org.smarttechie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id){

         productService.deleteProduct(id);


    }

    @PutMapping("product/{id}")
    @ResponseBody
    public Product updateProduct(@RequestBody Product product){

        return productService.update(product);

    }

    @GetMapping("/products")
    @ResponseBody
    public List<Product> getAllProducts(){

        return productService.getAllProducts();

    }
}
