package com.erichiroshi.dscatalog.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class UserDTO implements Serializable {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;

	private final Set<RoleDTO> roles = new HashSet<>();

}
