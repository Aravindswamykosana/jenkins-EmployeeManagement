package com.aravind.serviceImpl;

import org.springframework.http.ResponseEntity;

import com.aravind.entity.User1;

public interface UserService {

	public User1 registerUser(User1 user);

	public ResponseEntity<String> loginUser(String mail, String pwd);

	public ResponseEntity<String> forgotPwd(String mail);

	public String resetPwd(String token, String newPassword);
	
	public User1 getByEmailId(String email);

	public String changePassword(String email, String currentPassword, String newPassword, String confirmPassword);
}
