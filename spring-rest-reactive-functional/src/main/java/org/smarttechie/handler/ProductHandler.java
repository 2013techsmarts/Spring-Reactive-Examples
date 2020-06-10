package org.smarttechie.handler;

import org.smarttechie.model.Product;
import org.smarttechie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class ProductHandler {
    @Autowired
    private ProductService productService;
    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    /**
     * The handler to get all the available products.
     * @param serverRequest
     * @return - all the products info as part of ServerResponse
     */
    public Mono<ServerResponse> getAllProducts(ServerRequest serverRequest) {

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.getAllProducts(), Product.class);

    }

    /**
     * The handler to create a product
     * @param serverRequest
     * @return - return the created product as part of ServerResponse
     */
    public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {

        Mono<Product> productToSave = serverRequest.bodyToMono(Product.class);

        return productToSave.flatMap(product ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(productService.save(product), Product.class));

    }

    /**
     * The handler to delete a product based on the product id.
     * @param serverRequest
     * @return - return the deleted product as part of ServerResponse
     */
    public Mono<ServerResponse> deleteProduct(ServerRequest serverRequest) {

        String id = serverRequest.pathVariable("id");
        Mono<Void> deleteItem = productService.deleteProduct(Integer.parseInt(id));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(deleteItem, Void.class);
    }

    /**
     * The handler to update a product.
     * @param serverRequest
     * @return - The updated product as part of ServerResponse
     */
    public Mono<ServerResponse> updateProduct(ServerRequest serverRequest) {
        return productService.update(serverRequest.bodyToMono(Product.class)).flatMap(product ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromObject(product)))
                .switchIfEmpty(notFound);
    }
}
