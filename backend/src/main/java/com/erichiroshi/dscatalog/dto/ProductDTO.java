package com.erichiroshi.dscatalog.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProductDTO implements Serializable {

	private Long id;

	@Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
	@NotBlank(message = "Campo requerido")
	private String name;

	@NotBlank(message = "Campo requerido")
	private String description;

	@Positive(message = "Preço deve ser um valor positivo")
	private BigDecimal price;
	private String imgUrl;

	@PastOrPresent(message = "A data do produto não pode ser futura")
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDateTime date;

	@NotEmpty(message = "Produto sem categoria não é permitido")
	private final Set<CategoryDTO> categories = new HashSet<>();
}