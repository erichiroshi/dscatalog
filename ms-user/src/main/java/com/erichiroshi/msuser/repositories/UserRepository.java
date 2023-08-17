package com.erichiroshi.msuser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erichiroshi.msuser.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
