package com.patterns.cqrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patterns.cqrs.model.Product;
import com.patterns.cqrs.model.ProductQuery;
import com.patterns.cqrs.repositories.MaterializedViewRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

	private MaterializedViewRepository materializedRepository;

	@Autowired
	public ProductQueryServiceImpl(MaterializedViewRepository materializedRepository) {
		this.materializedRepository = materializedRepository;
	}

	@Transactional
	@Override
	public Mono<Product> getProductById(ProductQuery productQuery) {
		return materializedRepository.getProductById(productQuery.getId());

	}

	@Transactional
	@Override
	public Flux<Product> getAllProducts() {
		return materializedRepository.getAllProducts();

	}
}
