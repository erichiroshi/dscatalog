package com.erichiroshi.dscatalog.repositories;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.erichiroshi.dscatalog.entities.Product;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		long exintingId = 1L;

		repository.deleteById(exintingId);

		Optional<Product> result = repository.findById(exintingId);
		assertFalse(result.isPresent());
	}

	@Test
	public void deleteShouldNotThrowExcpetionWhenIdDoesNotExist() {
		long nonExintingId = 1000L;

		assertDoesNotThrow(() -> {
			repository.deleteById(nonExintingId);
		});
	}

}