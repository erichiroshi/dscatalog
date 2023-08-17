package com.erichiroshi.mscategory.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.erichiroshi.mscategory.dto.CategoryDTO;
import com.erichiroshi.mscategory.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	CategoryDTO toDTO(Category entity);

	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	Category toCategory(CategoryDTO dto);

	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "id", ignore = true)
	void update(CategoryDTO dto, @MappingTarget Category entity);

}
