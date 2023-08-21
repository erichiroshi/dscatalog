package com.erichiroshi.mscategory.mappers;

import com.erichiroshi.mscategory.dto.CategoryDTO;
import com.erichiroshi.mscategory.entities.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T09:15:32-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230721-1147, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toDTO(Category entity) {
        if ( entity == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( entity.getId() );
        categoryDTO.setName( entity.getName() );

        return categoryDTO;
    }

    @Override
    public Category toCategory(CategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( dto.getId() );
        category.setName( dto.getName() );

        return category;
    }

    @Override
    public void update(CategoryDTO dto, Category entity) {
        if ( dto == null ) {
            return;
        }

        entity.setName( dto.getName() );
    }
}
