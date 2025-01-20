package com.mingo.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mingo.entiry.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Serializable> {

	//it will find the user by userEmail(one of the attribute of userDetails entity)
	public UserDetails findByUserEmail(String email);
	public UserDetails findByUserEmailAndUserPassword(String email, String password);
}
