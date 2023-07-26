package com.erichiroshi.dscatalog.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.erichiroshi.dscatalog.dto.ProductDTO;
import com.erichiroshi.dscatalog.entities.Product;
import com.erichiroshi.dscatalog.repositories.ProductRepository;
import com.erichiroshi.dscatalog.services.exceptions.DatabaseException;
import com.erichiroshi.dscatalog.services.exceptions.ResourceNotFoundException;
import com.erichiroshi.dscatalog.tests.Factory;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductRepository repository;

	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private Product product;
	private ProductDTO productDTO;
	private PageImpl<Product> page;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		product = Factory.createProduct();
		productDTO = Factory.createProductDTO();
		page = new PageImpl<>(List.of(product));

		when(repository.findAll((Pageable) any())).thenReturn(page);

		when(repository.save(any())).thenReturn(product);

		when(repository.findById(existingId)).thenReturn(Optional.of(product));
		when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		when(repository.find(any(), any(), any())).thenReturn(page);

		doNothing().when(repository).deleteById(existingId);

		when(repository.findById(dependentId)).thenReturn(Optional.of(product));
		doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
	}

	@Test
	public void findAllPagedShouldReturnPage() {

		Pageable pageable = PageRequest.of(0, 12);

		Page<ProductDTO> result = service.findAllPaged(0L, "", pageable);

		assertNotNull(result);
	}

	@Test
	public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
		assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});
		verify(repository, times(1)).deleteById(dependentId);
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});

		verify(repository, times(0)).deleteById(nonExistingId);
	}

	@Test
	public void deleteShouldDoNothingWhenIdExists() {

		assertDoesNotThrow(() -> {
			service.delete(existingId);
		});

		verify(repository, times(1)).deleteById(existingId);
	}

	@Test
	@DisplayName("Deve retornar um ProductDTO Quando o id existir.")
	public void findById_OK_idExists() {

		ProductDTO result = service.findById(existingId);

		assertNotNull(result);
		assertEquals(existingId, result.getId());

		verify(repository, times(1)).findById(existingId);
	}

	@Test
	@DisplayName("Deve lançar ResourceNotFoundException quando o id não existir.")
	public void findById_Exception_idNotExists() {

		assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});

		verify(repository, times(1)).findById(nonExistingId);
	}

	@Test
	@DisplayName("Deve retornar um ProductDTO quando o id existir.")
	public void update_OK_idExist() {

		ProductDTO result = service.update(existingId, productDTO);

		assertNotNull(result);
		assertEquals(existingId, result.getId());
		verify(repository, times(1)).save(product);
	}

	@Test
	@DisplayName("Deve lançar uma ResourceNotFoundException quando o id não existir.")
	public void update_Exception_idNotExists() {

		assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, productDTO);
		});

		verify(repository, times(1)).findById(nonExistingId);
	}
}