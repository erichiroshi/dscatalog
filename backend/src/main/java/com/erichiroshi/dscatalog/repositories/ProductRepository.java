package com.erichiroshi.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erichiroshi.dscatalog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}