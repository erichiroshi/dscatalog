package com.erichiroshi.msuser.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RoleDTO implements Serializable {

	private Long id;
	private String authority;
}
