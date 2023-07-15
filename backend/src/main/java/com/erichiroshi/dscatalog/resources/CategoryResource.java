package com.erichiroshi.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erichiroshi.dscatalog.entities.Category;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = new ArrayList<>();
		list.add(Category.builder().id(1L).name("Books").build());
		list.add(Category.builder().id(2L).name("Eletronics").build());
		return ResponseEntity.ok(list);
	}
}
