package com.erichiroshi.dscatalog.dto;

import com.erichiroshi.dscatalog.services.validation.UserInsertValid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@UserInsertValid
public class UserInsertDTO extends UserDTO {

	private String password;
}
