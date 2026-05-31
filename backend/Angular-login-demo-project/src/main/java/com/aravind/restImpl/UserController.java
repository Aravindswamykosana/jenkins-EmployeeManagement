package com.aravind.restImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aravind.entity.User1;
import com.aravind.serviceImpl.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public ResponseEntity<User1> registerUser(@RequestBody User1 user){
		return new ResponseEntity<User1>(service.registerUser(user),HttpStatus.CREATED);
	}
	
	@PostMapping("/change-password")
	public ResponseEntity<Map<String, String>> changePassword(
	        @RequestParam String email,
	        @RequestParam String currentPassword,
	        @RequestParam String newPassword,
	        @RequestParam String confirmPassword) {

	    String result = service.changePassword(email, currentPassword, newPassword, confirmPassword);

	    Map<String, String> response = new HashMap<>();
	    response.put("message", result);

	    return ResponseEntity.ok(response);
	}

	
	@GetMapping("/login")
	public ResponseEntity<String> loginUser(@RequestParam String mail,@RequestParam String pwd){
		 return service.loginUser(mail, pwd);
	}
	
	@PostMapping("/forgotPwd")
	public ResponseEntity<String> forgotPwd(@RequestParam String email){
		return service.forgotPwd(email);
	}
	
	@PostMapping("/resetPwd")
	public ResponseEntity<String> resetPwd(@RequestParam String token ,@RequestParam String newPwd){
		return new ResponseEntity<String>(service.resetPwd(token,newPwd),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getByEmail")
	public User1 getByEmail1(@RequestParam String email) {
	    return service.getByEmailId(email);
	}	
}
