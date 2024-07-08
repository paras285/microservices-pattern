package com.patterns.cqrs.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.patterns.cqrs.controller.ProductCommandController;
import com.patterns.cqrs.controller.ProductQueryController;

@Configuration
public class ProductHandler {

	@Bean
	public RouterFunction<ServerResponse> commandRoute(ProductCommandController productCommandController) {
		return RouterFunctions.route()
				.POST("/commands/products", RequestPredicates.contentType(MediaType.APPLICATION_JSON),
						productCommandController::createProduct)
				.PUT("/commands/products/id", RequestPredicates.contentType(MediaType.APPLICATION_JSON),
						productCommandController::updateProduct)
				.build();
	}
	
	
	@Bean
	public RouterFunction<ServerResponse> queryRoute(ProductQueryController productQueryController) {
		return RouterFunctions.route()
				.GET("/query/products/{productId}",productQueryController::getProductById)
				.GET("/query/products",productQueryController::getAllProducts).build();
	}
}
