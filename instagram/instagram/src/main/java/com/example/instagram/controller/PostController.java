package com.example.instagram.controller;

import java.sql.Timestamp;

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

import com.example.instagram.dao.UserRepository;
import com.example.instagram.model.Post;
import com.example.instagram.model.User;
import com.example.instagram.service.PostService;

import jakarta.annotation.Nullable;

@RestController
@RequestMapping(value = "/post")
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping(value = "/post")
	public ResponseEntity<String> savePost(@RequestBody String postRequest) {

		Post post = setPost(postRequest);
		int postId = postService.savePost(post);
		return new ResponseEntity<String>(String.valueOf(postId), HttpStatus.CREATED);
	}

	@GetMapping(value = "/post")
	public ResponseEntity<String> getPost(@RequestParam String userId, @Nullable @RequestParam String postId) {

		JSONArray postArr = postService.getPost(Integer.valueOf(userId), postId);
		return new ResponseEntity<String>(postArr.toString(), HttpStatus.OK);
	}

	@PutMapping(value = "/post/{postId}")
	public ResponseEntity<String> updatePost(@PathVariable String postId, @RequestBody String postRequest) {

		Post post = setPost(postRequest);
		postService.updatePost(postId, post);
		return new ResponseEntity<>("Post updated", HttpStatus.OK);

	}

	private Post setPost(String postRequest) {
		JSONObject jsonObject = new JSONObject(postRequest);
		User user = null;
		int userId = jsonObject.getInt("userId");
		if (userRepository.findById(userId).isPresent()) {
			user = userRepository.findById(userId).get();
		} else {
			return null;
		}
		Post post = new Post();
		post.setUser(user);
		post.setPostData(jsonObject.getString("postData"));
		Timestamp createdTime = new Timestamp(System.currentTimeMillis());
		post.setCreatedDate(createdTime);
		return post;

	}
}
