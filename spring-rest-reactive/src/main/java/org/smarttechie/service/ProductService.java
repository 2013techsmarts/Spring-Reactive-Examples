package org.smarttechie.service;

import org.smarttechie.model.Product;
import org.smarttechie.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<Product> getAllProducts() {
        return productRepository.findAll().map(p -> {
            Product product = new Product();
            product.setId(p.getId());
            product.setTitle(p.getTitle());
            return product;
        });
    }

    public Mono<Product> save(Product product) {
        org.smarttechie.entity.Product p = new org.smarttechie.entity.Product();
        p.setId(new Random().nextInt());
        p.setTitle(product.getTitle());

        return productRepository.save(p).delayElement(Duration.ofMillis(3000)).map(p1 -> {
            Product product1 = new Product();
            product1.setId(p1.getId());
            product1.setTitle(p1.getTitle());

            return product1;
        });
    }

    public Mono<Void> deleteProduct(int id) {
        return productRepository.deleteById(id);
    }

    public Mono<ResponseEntity<Product>> update(Product product) {
        System.out.println(product.getId());
        System.out.println(product.getTitle());
        return productRepository.findById(product.getId())
                .flatMap(p -> {
                    System.out.println(p.getId());
                    System.out.println(p.getTitle());
                    p.setTitle(product.getTitle());
                    return productRepository.save(p);
                })
                .map(updatedItem -> {
                    Product product1 = new Product();
                    product1.setId(updatedItem.getId());
                    product1.setTitle(updatedItem.getTitle());
                    return new ResponseEntity<>(product1, HttpStatus.OK);
                })
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

