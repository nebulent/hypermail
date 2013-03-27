package com.nebulent.hypermail.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nebulent.hypermail.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
	
	User findByUsername(String username);
	
}
