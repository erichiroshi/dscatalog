package com.erichiroshi.dscatalog.repositories;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.erichiroshi.dscatalog.entities.Product;
import com.erichiroshi.dscatalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;

	private long exintingId;
	private long nonExintingId;
	private long countTotalProducts;

	@BeforeEach
	void setUp() throws Exception {
		exintingId = 1L;
		nonExintingId = 1000L;
		countTotalProducts = 25L;
	}

	@Test
	@DisplayName("Deve salvar e auto incrementar id Quando id é null.")
	public void save_OK_idIsNull() {

		Product product = Factory.createProduct();
		product.setId(null);

		product = repository.save(product);

		assertNotNull(product.getId());
		assertEquals(countTotalProducts + 1, product.getId());
	}

	@Test
	@DisplayName("Deve deletar Quando existir um Id.")
	public void delete_OK_idExists() {
		repository.deleteById(exintingId);

		Optional<Product> result = repository.findById(exintingId);
		assertFalse(result.isPresent());
	}

	@Test
	@DisplayName("Não deve lançar Exception Quando o Id não existir.")
	public void deleteShouldNotThrowExcpetionWhenIdDoesNotExist() {

		assertDoesNotThrow(() -> {
			repository.deleteById(nonExintingId);
		});
	}

	@Test
	@DisplayName("Deve retornar um Optional<Product> não vazio Quando o id existir.")
	public void findById_OK_idExists() {
		Optional<Product> result = repository.findById(exintingId);

		assertNotNull(result);
		assertTrue(result.isPresent());
		assertEquals(exintingId, result.get().getId());
	}

	@Test
	@DisplayName("Deve retornar um Optional<Product> vazio Quando o id não existir.")
	public void findById_Empty_idNotExists() {
		Optional<Product> result = repository.findById(nonExintingId);

		assertTrue(result.isEmpty());
	}

}