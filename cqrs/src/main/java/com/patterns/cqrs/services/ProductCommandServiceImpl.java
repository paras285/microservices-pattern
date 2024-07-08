package com.patterns.cqrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patterns.cqrs.model.Product;
import com.patterns.cqrs.model.ProductCreateCommand;
import com.patterns.cqrs.model.ProductUpdateCommand;
import com.patterns.cqrs.repositories.ProductRepository;

import reactor.core.publisher.Mono;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductCommandServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

//	@Transactional
	@Override
	public Mono<Product> createProduct(ProductCreateCommand productCreateCommand) {
		Product product = new Product();
		product.setName(productCreateCommand.getName());
		product.setPrice(productCreateCommand.getValue());
		return productRepository.save(product);
	}

	@Transactional
	@Override
	public Mono<Product> updateProduct(ProductUpdateCommand productUpdateCommand) {
		return productRepository.findById(productUpdateCommand.getId())
		.switchIfEmpty(Mono.error(new RuntimeException("Product not found")))
		.flatMap(existingProduct-> {
			existingProduct.setName(productUpdateCommand.getName());
			existingProduct.setPrice(productUpdateCommand.getValue());
			return productRepository.save(existingProduct);
		});
	}

}
