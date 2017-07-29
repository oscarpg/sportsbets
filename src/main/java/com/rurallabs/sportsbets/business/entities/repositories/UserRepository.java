package com.rurallabs.sportsbets.business.entities.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rurallabs.sportsbets.business.entities.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

	public List<User> findByGlobalAdminTrue();
	
	public boolean existsByEmail(String email);

	public User findByEmail(String email);

}
