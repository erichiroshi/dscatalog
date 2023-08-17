package com.erichiroshi.dscatalog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.erichiroshi.dscatalog.dto.ProductDTO;
import com.erichiroshi.dscatalog.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	@Mapping(source = "categories", target = "categories", ignore = true)
	Product toProduct(ProductDTO dto);

	ProductDTO toDTO(Product entity);

	@Mapping(target = "id", ignore = true)
	void update(ProductDTO dto, @MappingTarget ProductDTO entityDTO);

}