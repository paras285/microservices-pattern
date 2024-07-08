package com.patterns.cqrs.services;

import com.patterns.cqrs.model.Product;
import com.patterns.cqrs.model.ProductCreateCommand;
import com.patterns.cqrs.model.ProductUpdateCommand;

import reactor.core.publisher.Mono;

public interface ProductCommandService {

	public Mono<Product> createProduct(ProductCreateCommand productCreateCommand);
	
	public Mono<Product> updateProduct(ProductUpdateCommand productUpdateCommand);
}
