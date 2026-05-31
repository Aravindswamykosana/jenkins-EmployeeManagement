package com.aravind.serviceImpl;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aravind.entity.User1;
import com.aravind.exception.InvalidUserDataException;
import com.aravind.repo.User1Repo;
import com.aravind.utils.EmailUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private User1Repo repo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailUtils util;

	@Override
	public User1 registerUser(User1 user) {
		log.info("inside registerUser::");
		User1 mail = repo.findByEmail(user.getEmail());
		try {
			if (validate(user)) {
				if (mail  == null) {
					String encodedPwd = passwordEncoder.encode(user.getPassword());
					user.setPassword(encodedPwd);
					 User1 savedUser = repo.save(user);
		             util.sendWelcomeEmail(savedUser.getEmail(), savedUser.getName());
		             return savedUser;
				} else {
					log.info("User with email already exists: " + user.getEmail());
					throw new InvalidUserDataException();
				}

			} else {
				log.info("Validation failed for user: " + user);
				throw new InvalidUserDataException();
			}
		} catch (Exception e) {
			log.info("inside registerUser::" + e.getMessage());
			return null;
		}
	}

	public boolean validate(User1 user) {
		return user != null && user.getName() != null && !user.getName().isEmpty() && user.getEmail() != null
				&& !user.getEmail().isEmpty() && user.getPassword() != null && !user.getPassword().isEmpty()
				&& user.getContactNum() != 0;
	}

	@Override
	public ResponseEntity<String> loginUser(String mail, String pwd) {
	    User1 data = repo.findByEmail(mail);

	    if (data != null && data.getPassword() != null) {
	        if (passwordEncoder.matches(pwd, data.getPassword())) {
	            return ResponseEntity.ok("login success....!");
	        }
	    }

	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                         .body("invalid credentials..!");
	}


	@Override
	public String changePassword(String email, String currentPassword, String newPassword, String confirmPassword) {
		User1 user = repo.findByEmail(email);
        if (user == null) {
            return "User not found!";
        }
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return "Current password is incorrect!";
        }

        if (!newPassword.equals(confirmPassword)) {
            return "New password and confirm password do not match!";
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        repo.save(user);

        return "Password changed successfully!";
    }

	@Override
	public ResponseEntity<String> forgotPwd(String email) {
	    User1 user = repo.findByEmail(email);
	    if (user != null) {
	        String token = UUID.randomUUID().toString();
	        user.setResetToken(token);
	        repo.save(user);
	        util.sendResetEmail(user.getEmail(), token);
	        return ResponseEntity.ok("Reset password link sent to your email.");
	    }
	    return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                         .body("Email not found!");
	}


	@Override
	public String resetPwd(String token, String newPassword) {
        User1 user = repo.findByResetToken(token);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setResetToken(null); // clear token
            repo.save(user);
            return "Password reset successful!";
        }
        return "Invalid or expired reset token!";
    }

	@Override
	public User1 getByEmailId(String email) {
		User1 user = repo.findByEmail(email);
		String pwd = user.getPassword();
		String visible = pwd.substring(0, 2);
        String masked = "*".repeat(pwd.length() - 2);
        user.setPassword(visible + masked);
		return user;
	}


}
