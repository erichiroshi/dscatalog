package com.erichiroshi.dscatalog.tests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.erichiroshi.dscatalog.dto.ProductDTO;
import com.erichiroshi.dscatalog.entities.Category;
import com.erichiroshi.dscatalog.entities.Product;
import com.erichiroshi.dscatalog.mappers.ProductMapper;
import com.erichiroshi.dscatalog.mappers.ProductMapperImpl;

public class Factory {

	private static ProductMapper mapper = new ProductMapperImpl();

	public static Product createProduct() {
		Product product = new Product(1L, "Phone", LocalDateTime.of(2020, 10, 20, 03, 00, 00), BigDecimal.valueOf(800.0), "Good Phone", "https://img.com/img.png");
		product.getCategories().add(new Category(2L, "Electronics"));
		return product;
	}

	public static ProductDTO createProductDTO() {
		Product product = createProduct();
		return mapper.toDTO(product);
	}

}