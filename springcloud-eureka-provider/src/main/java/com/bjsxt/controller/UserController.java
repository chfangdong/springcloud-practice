package com.bjsxt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjsxt.pojo.User;

@RestController
public class UserController {
	@RequestMapping("/users")
	public List<User> getUsers() {
		List<User> list = new ArrayList<>();
		list.add(new User(1, "zhangsan", 20));
		list.add(new User(2, "lisi", 22));
		list.add(new User(3, "wangwu", 30));
		return list;
	}
}
