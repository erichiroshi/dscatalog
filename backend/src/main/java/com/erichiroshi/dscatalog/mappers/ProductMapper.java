package com.erichiroshi.dscatalog.mappers;

import org.mapstruct.Mapper;

import com.erichiroshi.dscatalog.dto.ProductDTO;
import com.erichiroshi.dscatalog.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDTO dto);

    ProductDTO toDTO(Product entity);
   
}