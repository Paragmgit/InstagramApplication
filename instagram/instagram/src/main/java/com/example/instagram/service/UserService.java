package com.example.instagram.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram.dao.UserRepository;
import com.example.instagram.model.User;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public int saveUser(User users) {
		User userObj = userRepository.save(users);
		return userObj.getUserId();
	}

	public JSONArray getAllUser(String userId) {
		JSONArray userArray = new JSONArray();
		if (null != userId && userRepository.findById(Integer.valueOf(userId)).isPresent()) {

			User user = userRepository.findById(Integer.valueOf(userId)).get();
			JSONObject userObj = setUser(user);
			userArray.put(userObj);
		} else {
			List<User> userList = userRepository.findAll();
			for (User user : userList) {
				JSONObject userObj = setUser(user);
				userArray.put(userObj);
			}
		}
		return userArray;
	}

	private JSONObject setUser(User user) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", user.getUserId());
		jsonObject.put("firstName", user.getFirstName());
		jsonObject.put("lastName", user.getLastName());
		jsonObject.put("age", user.getAge());
		jsonObject.put("email", user.getEmail());
		jsonObject.put("phoneNumber", user.getPhoneNumber());

		return jsonObject;
	}

	public void updateUser(String userId, User newUser) {
		if(userRepository.findById(Integer.valueOf(userId)).isPresent()) {
			User user = userRepository.findById(Integer.valueOf(userId)).get();
			newUser.setUserId(user.getUserId());
			userRepository.save(newUser);
		}
		
	}

}
