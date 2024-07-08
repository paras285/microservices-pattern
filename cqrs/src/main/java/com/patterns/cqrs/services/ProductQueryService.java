package com.patterns.cqrs.services;

import com.patterns.cqrs.model.Product;
import com.patterns.cqrs.model.ProductQuery;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductQueryService {
	
	public Mono<Product> getProductById(ProductQuery productQuery);
	public Flux<Product> getAllProducts();

}
