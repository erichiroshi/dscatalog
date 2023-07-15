package com.erichiroshi.dscatalog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.erichiroshi.dscatalog.dto.CategoryDTO;
import com.erichiroshi.dscatalog.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	CategoryDTO toDTO(Category entity);

	Category toCategory(CategoryDTO dto);

	@Mapping(target = "id", ignore = true)
    void update(CategoryDTO dto, @MappingTarget Category entity);

}
