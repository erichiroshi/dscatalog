package com.erichiroshi.msuser.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erichiroshi.msuser.dto.UserDTO;
import com.erichiroshi.msuser.dto.UserInsertDTO;
import com.erichiroshi.msuser.dto.UserUpdateDTO;
import com.erichiroshi.msuser.entities.User;
import com.erichiroshi.msuser.mappers.UserMapper;
import com.erichiroshi.msuser.mappers.UserMapperImpl;
import com.erichiroshi.msuser.repositories.UserRepository;
import com.erichiroshi.msuser.services.exceptions.DatabaseException;
import com.erichiroshi.msuser.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private UserMapper mapper = new UserMapperImpl();

	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		Page<UserDTO> listDTO = list.map(x -> mapper.toDTO(x));
		return listDTO;
	}

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		User entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Entity not found. Id = " + id));
		return mapper.toDTO(entity);
	}

	public UserDTO findByEmail(String email) {
		User entity = repository.findByEmail(email);
		return mapper.toDTO(entity);
	}
	
	public User findByEmailUser(String email) {
		User entity = repository.findByEmail(email);
		return entity;
	}

	@Transactional
	public UserDTO insert(UserInsertDTO insertDTO) {
		User entity = mapper.toUser(insertDTO);
		entity.setPassword(passwordEncoder.encode(insertDTO.getPassword()));
		entity = repository.save(entity);
		return mapper.toDTO(entity);
	}

	@Transactional
	public UserDTO update(Long id, UserUpdateDTO updateDTO) {
		UserDTO entityDTO = findById(id);
		mapper.update(updateDTO, entityDTO);
		User entity = mapper.toUser(entityDTO);
		entity = repository.save(entity);
		return mapper.toDTO(entity);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Entity not found. Id = " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

}
