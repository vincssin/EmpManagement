package com.employe.api.service;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.employe.api.model.Employee;
import com.employe.api.model.User;
import com.employe.api.mongo.dao.UserRepository;

@Service
public class UserService {
	private HashMap<String, Object> map = new HashMap<>();
	private UserRepository userRepository;
	private TokenService tokenService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	UserService(UserRepository userRepository, TokenService tokenService) {
		this.userRepository = userRepository;
		this.tokenService = tokenService;
	}

	public Object getUser(ObjectId userId) {
		map = new HashMap<>();
		System.out.println("Get-User: " + userId);
		
		Object existObj = userRepository.findOne(userId);
		
		System.out.println("User:  "+existObj);
		if (existObj != null) {
			return existObj;
		}else {
			map.put("message", "Invalid user");
			return map;
		}
	}

	public Object getAuthorize(String token) {
		map = new HashMap<>();
		System.out.println("Get-Authorize: " + token);
		if (token != null && token != "") {
			HashMap tokenMap = tokenService.isTokenValid(token);
			System.out.println("tokenMap: "+tokenMap);
			if (tokenMap.get("status").toString().equalsIgnoreCase("failed")) {
				map.put("isAuthorized", "false");
				map.put("message", "Authentication Failed: " + tokenMap.get("message").toString());
			} else if (tokenMap.get("status").toString().equalsIgnoreCase("success")) {
				map.put("isAuthorized", "true");
			}
		}else {
			map.put("isAuthorized", "false");
			map.put("message", "Authentication Failed: Authorization header not found Please check and try again");
		}
		return map;
	}

	public Object loginUser(User user) {
		map = new HashMap<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(user.getUsername()));
		Object existObj = mongoTemplate.findOne(query, User.class);

		System.out.println("existObj: " + existObj);

		if (existObj != null) {
			User loginUser = (User) existObj;
			if (loginUser.getUsername().toString().equalsIgnoreCase(user.getUsername())
					&& loginUser.getPassword().toString().equalsIgnoreCase(user.getPassword())) {
				loginUser.setLastLoginAt(new Date());
				User savedUser = userRepository.save(loginUser);
				map.put("message", "Login successfully");
				map.put("token", tokenService.createToken(savedUser.getId()));
				map.put("role", savedUser.getRole());
			} else {
				map.put("message", "Invalid Username or Password");
			}

		} else {
			map.put("message", "Invalid Username or Password");
		}
		return map;
	}

	public Object saveUser(User user) {
		map = new HashMap<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(user.getUsername()));
		Object existObj = mongoTemplate.findOne(query, User.class);

		System.out.println("existObj: " + existObj);

		if (existObj != null) {
			map.put("message", "username already exists");
		} else {
			user.setCreatedAt(new Date());
			user.setLastLoginAt(new Date());
			User savedUser = userRepository.save(user);
			map.put("message", "Register successfully");
			map.put("token", tokenService.createToken(savedUser.getId()));
		}

		return map;
	}
}