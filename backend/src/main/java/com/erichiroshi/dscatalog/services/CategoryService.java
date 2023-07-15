package com.erichiroshi.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erichiroshi.dscatalog.dto.CategoryDTO;
import com.erichiroshi.dscatalog.entities.Category;
import com.erichiroshi.dscatalog.mappers.CategoryMapper;
import com.erichiroshi.dscatalog.repositories.CategoryRepository;
import com.erichiroshi.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Autowired
	private CategoryMapper mapper;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		return list.stream().map(x -> mapper.toDTO(x)).toList();
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Category entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Entity not found. Id: " + id));
		return mapper.toDTO(entity);
	}
}
