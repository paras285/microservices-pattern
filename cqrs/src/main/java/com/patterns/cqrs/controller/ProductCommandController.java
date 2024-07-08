package com.patterns.cqrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.patterns.cqrs.model.ProductCreateCommand;
import com.patterns.cqrs.model.ProductUpdateCommand;
import com.patterns.cqrs.services.ProductCommandService;

import reactor.core.publisher.Mono;

@Component
public class ProductCommandController {

	private ProductCommandService productCommandService;
	
	@Autowired
	public ProductCommandController(ProductCommandService productCommandService) {
		this.productCommandService = productCommandService;
	}

	public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(ProductCreateCommand.class)
				.flatMap(productCreateCommand -> productCommandService.createProduct(productCreateCommand))
				.flatMap(product -> ServerResponse.ok().bodyValue(product));
	}

	public Mono<ServerResponse> updateProduct(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(ProductUpdateCommand.class)
				.flatMap(productUpdateCommand -> productCommandService.updateProduct(productUpdateCommand))
				.flatMap(product -> ServerResponse.ok().bodyValue(product));
	}
}
