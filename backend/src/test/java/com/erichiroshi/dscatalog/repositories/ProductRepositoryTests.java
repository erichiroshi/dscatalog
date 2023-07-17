package com.erichiroshi.dscatalog.repositories;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.erichiroshi.dscatalog.entities.Product;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;

	private long exintingId;
	private long nonExintingId;

	@BeforeEach
	void setUp() throws Exception {
		exintingId = 1L;
		nonExintingId = 1000L;
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		repository.deleteById(exintingId);

		Optional<Product> result = repository.findById(exintingId);
		assertFalse(result.isPresent());
	}

	@Test
	public void deleteShouldNotThrowExcpetionWhenIdDoesNotExist() {

		assertDoesNotThrow(() -> {
			repository.deleteById(nonExintingId);
		});
	}

}