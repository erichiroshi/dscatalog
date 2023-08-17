package com.erichiroshi.msuser.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.erichiroshi.msuser.dto.UserDTO;
import com.erichiroshi.msuser.dto.UserUpdateDTO;
import com.erichiroshi.msuser.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "password", ignore = true)
	User toUser(UserDTO dto);

	UserDTO toDTO(User entity);

	@Mapping(target = "id", ignore = true)
    void update(UserUpdateDTO updateDTO, @MappingTarget UserDTO entityDTO);
}