package com.patterns.cqrs.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.patterns.cqrs.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MaterializedViewRepository extends ReactiveCrudRepository<Product, Long> {

	@Query(value = "SELECT * FROM MATERIALIZED_VIEW_PRODUCTS")
	Flux<Product> getAllProducts();
	
	@Query(value = "SELECT * from MATERIALIZED_VIEW_PRODUCTS WHERE ID = :id")
	Mono<Product> getProductById(Long id);
}
