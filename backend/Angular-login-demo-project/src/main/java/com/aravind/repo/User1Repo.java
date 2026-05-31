package com.aravind.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aravind.entity.User1;

public interface User1Repo extends JpaRepository<User1, Integer>{

	public User1 findByEmail(String email);
	User1 findByResetToken(String resetToken);
}
