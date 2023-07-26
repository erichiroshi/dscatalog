package com.erichiroshi.dscatalog.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erichiroshi.dscatalog.dto.ProductDTO;
import com.erichiroshi.dscatalog.entities.Category;
import com.erichiroshi.dscatalog.entities.Product;
import com.erichiroshi.dscatalog.mappers.ProductMapper;
import com.erichiroshi.dscatalog.mappers.ProductMapperImpl;
import com.erichiroshi.dscatalog.repositories.CategoryRepository;
import com.erichiroshi.dscatalog.repositories.ProductRepository;
import com.erichiroshi.dscatalog.services.exceptions.DatabaseException;
import com.erichiroshi.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;

	private ProductMapper mapper = new ProductMapperImpl();

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(Long categoryId, String name, Pageable pageable) {
		List<Category> categories = (categoryId == 0) ? null : Arrays.asList(categoryRepository.getOne(categoryId));
		Page<Product> page = repository.find(categories, name, pageable);
		repository.findProductsWithCategories(page.getContent());
		return page.map(x -> mapper.toDTO(x));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found. Id: " + id));
		return mapper.toDTO(entity);
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = mapper.toProduct(dto);
		entity = repository.save(entity);
		return mapper.toDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		ProductDTO entityDTO = findById(id);
		mapper.update(dto, entityDTO);
		Product entity = mapper.toProduct(entityDTO);
		entity = repository.save(entity);
		return mapper.toDTO(entity);
	}

	@Transactional
	public void delete(Long id) {
		try {
			findById(id);
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity constraint violation");
		}
	}
}
