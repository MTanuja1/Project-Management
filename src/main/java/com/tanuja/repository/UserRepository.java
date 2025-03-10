package com.tanuja.repository;

import com.tanuja.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,
		Long> {

	User  findByEmail(String email);
}
