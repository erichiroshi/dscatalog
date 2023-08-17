package com.erichiroshi.msproduct.services;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.erichiroshi.msproduct.dto.ProductDTO;
import com.erichiroshi.msproduct.dto.UriDTO;
import com.erichiroshi.msproduct.entities.Category;
import com.erichiroshi.msproduct.entities.Product;
import com.erichiroshi.msproduct.mappers.ProductMapper;
import com.erichiroshi.msproduct.mappers.ProductMapperImpl;
import com.erichiroshi.msproduct.repositories.CategoryRepository;
import com.erichiroshi.msproduct.repositories.ProductRepository;
import com.erichiroshi.msproduct.services.exceptions.DatabaseException;
import com.erichiroshi.msproduct.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private S3Service s3Service;

	private ProductMapper mapper = new ProductMapperImpl();

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(Long categoryId, String name, Pageable pageable) {
		List<Category> categories = (categoryId == 0) ? null : Arrays.asList(categoryRepository.getReferenceById(categoryId));
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
	
	public UriDTO uploadFile(MultipartFile file) {
		URL url = s3Service.uploadFile(file);
		return new UriDTO(url.toString());
	}
}