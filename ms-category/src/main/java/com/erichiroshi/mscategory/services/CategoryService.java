package com.erichiroshi.mscategory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erichiroshi.mscategory.dto.CategoryDTO;
import com.erichiroshi.mscategory.entities.Category;
import com.erichiroshi.mscategory.mappers.CategoryMapper;
import com.erichiroshi.mscategory.repositories.CategoryRepository;
import com.erichiroshi.mscategory.services.exceptions.DatabaseException;
import com.erichiroshi.mscategory.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Autowired
	private CategoryMapper mapper;

	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(Pageable pageable) {
		Page<Category> list = repository.findAll(pageable);
		return list.map(x -> mapper.toDTO(x));
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Category entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Entity not found. Id: " + id));
		return mapper.toDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = repository.save(mapper.toCategory(dto));
		return mapper.toDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		CategoryDTO entityDTO = findById(id);
		Category entity = mapper.toCategory(entityDTO);
		mapper.update(dto, entity);
		entity = repository.save(entity);
		return mapper.toDTO(entity);
	}

	@Transactional
	public void delete(Long id) {
		try {
			findById(id);
			repository.deleteById(id);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity constraint violation");
		}
	}
}
