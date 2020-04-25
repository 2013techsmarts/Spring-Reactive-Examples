package org.smarttechie.service;

import org.smarttechie.model.Product;
import org.smarttechie.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        org.smarttechie.entity.Product prod = new org.smarttechie.entity.Product();
        prod.setId(new Random().nextInt());
        prod.setTitle(product.getTitle());
        productRepository.save(prod);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().map(p -> {
            Product product = new Product();
            product.setId(p.getId());
            product.setTitle(p.getTitle());
            return product;
        }).collect(Collectors.toList());
    }
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Product update(Product product) {
        productRepository.findById(product.getId()).map(p ->{
            p.setTitle(product.getTitle());
            productRepository.save(p);
            return product;
        });
        return product;
    }
}
