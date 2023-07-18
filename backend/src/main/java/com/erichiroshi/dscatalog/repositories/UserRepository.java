package com.erichiroshi.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erichiroshi.dscatalog.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
