package com.patterns.cqrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.patterns.cqrs.model.Product;
import com.patterns.cqrs.model.ProductQuery;
import com.patterns.cqrs.services.ProductQueryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductQueryController {

	private ProductQueryService productQueryService;
	
	@Autowired
	public ProductQueryController(ProductQueryService productQueryService) {
		this.productQueryService = productQueryService;
	}

	public Mono<ServerResponse> getProductById(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(ProductQuery.class)
				.flatMap(productQuery -> productQueryService.getProductById(productQuery))
				.flatMap(product -> ServerResponse.ok().bodyValue(product));
	}

	public Mono<ServerResponse> getAllProducts(ServerRequest serverRequest) {
		Flux<Product> allProduct = productQueryService.getAllProducts();
		return ServerResponse.ok().body(allProduct, Product.class);
	}

}
