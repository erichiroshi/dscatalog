package com.erichiroshi.dscatalog.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoryDTO implements Serializable {

	private Long id;
	private String name;
}
