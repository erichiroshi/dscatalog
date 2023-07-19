package com.erichiroshi.dscatalog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.erichiroshi.dscatalog.dto.UserDTO;
import com.erichiroshi.dscatalog.dto.UserUpdateDTO;
import com.erichiroshi.dscatalog.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "authorities", ignore = true)
	@Mapping(target = "password", ignore = true)
	User toUser(UserDTO dto);

	UserDTO toDTO(User entity);

	@Mapping(target = "id", ignore = true)
    void update(UserUpdateDTO updateDTO, @MappingTarget UserDTO entityDTO);
}