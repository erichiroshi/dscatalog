package com.erichiroshi.dscatalog.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductDTO implements Serializable {

	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private String imgUrl;

	@JsonFormat(pattern = "dd/MM/yyyy hh:MM:ss")
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDateTime date;

	private final Set<CategoryDTO> categories = new HashSet<>();
}