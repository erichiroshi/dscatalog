package com.erichiroshi.mscategory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erichiroshi.mscategory.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
