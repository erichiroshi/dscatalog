package com.erichiroshi.msproduct.mappers;

import com.erichiroshi.msproduct.dto.CategoryDTO;
import com.erichiroshi.msproduct.dto.ProductDTO;
import com.erichiroshi.msproduct.entities.Category;
import com.erichiroshi.msproduct.entities.Product;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T09:15:34-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230721-1147, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        if ( product.getCategories() != null ) {
            Set<Category> set = mapCategoryDTOsToCategories( dto.getCategories() );
            if ( set != null ) {
                product.getCategories().addAll( set );
            }
        }
        product.setDate( dto.getDate() );
        product.setDescription( dto.getDescription() );
        product.setId( dto.getId() );
        product.setImgUrl( dto.getImgUrl() );
        product.setName( dto.getName() );
        product.setPrice( dto.getPrice() );

        return product;
    }

    @Override
    public ProductDTO toDTO(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setDate( entity.getDate() );
        productDTO.setDescription( entity.getDescription() );
        productDTO.setId( entity.getId() );
        productDTO.setImgUrl( entity.getImgUrl() );
        productDTO.setName( entity.getName() );
        productDTO.setPrice( entity.getPrice() );
        if ( productDTO.getCategories() != null ) {
            Set<CategoryDTO> set = categorySetToCategoryDTOSet( entity.getCategories() );
            if ( set != null ) {
                productDTO.getCategories().addAll( set );
            }
        }

        return productDTO;
    }

    @Override
    public void update(ProductDTO dto, ProductDTO entityDTO) {
        if ( dto == null ) {
            return;
        }

        entityDTO.setDate( dto.getDate() );
        entityDTO.setDescription( dto.getDescription() );
        entityDTO.setImgUrl( dto.getImgUrl() );
        entityDTO.setName( dto.getName() );
        entityDTO.setPrice( dto.getPrice() );
        if ( entityDTO.getCategories() != null ) {
            entityDTO.getCategories().clear();
            Set<CategoryDTO> set = dto.getCategories();
            if ( set != null ) {
                entityDTO.getCategories().addAll( set );
            }
        }
    }

    protected CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );

        return categoryDTO;
    }

    protected Set<CategoryDTO> categorySetToCategoryDTOSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryDTO> set1 = new LinkedHashSet<CategoryDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( categoryToCategoryDTO( category ) );
        }

        return set1;
    }
}
