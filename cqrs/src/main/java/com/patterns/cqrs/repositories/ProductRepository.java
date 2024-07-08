package com.patterns.cqrs.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.patterns.cqrs.model.Product;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

}
