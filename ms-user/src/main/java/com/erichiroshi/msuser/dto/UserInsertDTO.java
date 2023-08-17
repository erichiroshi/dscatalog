package com.erichiroshi.msuser.dto;

import com.erichiroshi.msuser.services.validation.UserInsertValid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@UserInsertValid
public class UserInsertDTO extends UserDTO {

	private String password;
}
