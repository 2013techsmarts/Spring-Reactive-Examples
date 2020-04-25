package org.smarttechie.router;

import org.smarttechie.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> productsRoute(ProductHandler productHandler){

        return RouterFunctions
                .route(GET("/products").and(accept(MediaType.APPLICATION_JSON))
                        ,productHandler::getAllProducts)
                .andRoute(POST("/product").and(accept(MediaType.APPLICATION_JSON))
                        ,productHandler::createProduct)
                .andRoute(DELETE("/product/{id}").and(accept(MediaType.APPLICATION_JSON))
                        ,productHandler::deleteProduct)
                .andRoute(PUT("/product/{id}").and(accept(MediaType.APPLICATION_JSON))
                        ,productHandler::updateProduct);
    }
}
