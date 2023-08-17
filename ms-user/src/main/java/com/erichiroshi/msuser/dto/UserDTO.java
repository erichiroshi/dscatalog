package com.erichiroshi.msuser.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO implements Serializable {

	private Long id;
	
    @NotBlank(message = "Campo obrigatório")
	private String firstName;
	private String lastName;
	
	@Email(message = "Favor entrar um email válido")
	private String email;

	private final Set<RoleDTO> roles = new HashSet<>();

}
