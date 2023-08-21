package com.erichiroshi.msuser.mappers;

import com.erichiroshi.msuser.dto.RoleDTO;
import com.erichiroshi.msuser.dto.UserDTO;
import com.erichiroshi.msuser.dto.UserUpdateDTO;
import com.erichiroshi.msuser.entities.Role;
import com.erichiroshi.msuser.entities.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T09:15:36-0400",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230721-1147, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( dto.getEmail() );
        user.setFirstName( dto.getFirstName() );
        user.setId( dto.getId() );
        user.setLastName( dto.getLastName() );
        if ( user.getRoles() != null ) {
            Set<Role> set = roleDTOSetToRoleSet( dto.getRoles() );
            if ( set != null ) {
                user.getRoles().addAll( set );
            }
        }

        return user;
    }

    @Override
    public UserDTO toDTO(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setEmail( entity.getEmail() );
        userDTO.setFirstName( entity.getFirstName() );
        userDTO.setId( entity.getId() );
        userDTO.setLastName( entity.getLastName() );
        if ( userDTO.getRoles() != null ) {
            Set<RoleDTO> set = roleSetToRoleDTOSet( entity.getRoles() );
            if ( set != null ) {
                userDTO.getRoles().addAll( set );
            }
        }

        return userDTO;
    }

    @Override
    public void update(UserUpdateDTO updateDTO, UserDTO entityDTO) {
        if ( updateDTO == null ) {
            return;
        }

        entityDTO.setEmail( updateDTO.getEmail() );
        entityDTO.setFirstName( updateDTO.getFirstName() );
        entityDTO.setLastName( updateDTO.getLastName() );
        if ( entityDTO.getRoles() != null ) {
            entityDTO.getRoles().clear();
            Set<RoleDTO> set = updateDTO.getRoles();
            if ( set != null ) {
                entityDTO.getRoles().addAll( set );
            }
        }
    }

    protected Role roleDTOToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setAuthority( roleDTO.getAuthority() );
        role.setId( roleDTO.getId() );

        return role;
    }

    protected Set<Role> roleDTOSetToRoleSet(Set<RoleDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDTO roleDTO : set ) {
            set1.add( roleDTOToRole( roleDTO ) );
        }

        return set1;
    }

    protected RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setAuthority( role.getAuthority() );
        roleDTO.setId( role.getId() );

        return roleDTO;
    }

    protected Set<RoleDTO> roleSetToRoleDTOSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDTO> set1 = new LinkedHashSet<RoleDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleDTO( role ) );
        }

        return set1;
    }
}
