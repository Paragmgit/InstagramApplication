package com.example.instagram.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagram.model.User;
import com.example.instagram.service.UserService;

import jakarta.annotation.Nullable;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/createUser")
	public ResponseEntity<String> createUser(@RequestBody String userData){
		User users = setUser(userData);
		int userId = userService.saveUser(users);
		return new ResponseEntity<String>("user saved"+userId,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/users")
	public ResponseEntity<String> getUser(@Nullable @RequestParam String userId){
		JSONArray userDetails = userService.getAllUser(userId);
		return new ResponseEntity<String>(userDetails.toString(),HttpStatus.OK);
	}
	
	@PutMapping(value = "/users/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable String userId,@RequestBody String requestData){
		User user = setUser(requestData);
		userService.updateUser(userId,user);
		return new ResponseEntity<String>("updated user",HttpStatus.OK);
	}

	private User setUser(String userData) {
		JSONObject jsonObject = new JSONObject(userData);
		User user = new User();
		user.setAge(jsonObject.getInt("age"));
		user.setEmail(jsonObject.getString("email"));
		user.setFirstName(jsonObject.getString("firstName"));
		user.setLastName(jsonObject.getString("lastName"));
		user.setPhoneNumber(jsonObject.getString("phoneNumber"));
		return user;
	}

}
