package com.erichiroshi.dscatalog.mappers;

import org.mapstruct.Mapper;

import com.erichiroshi.dscatalog.dto.CategoryDTO;
import com.erichiroshi.dscatalog.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	CategoryDTO toDTO(Category entity);

	Category toCategory(CategoryDTO dto);

}
