package com.cos.reflect.controller;

import com.cos.reflect.anno.RequestMapping;
import com.cos.reflect.dto.JoinDto;
import com.cos.reflect.dto.LoginDto;
import com.cos.reflect.model.User;

public class UserController {

	@RequestMapping("/user/join") // 해당 애노테이션의 value함수 값으로 들어간다.
	public String join(JoinDto dto) {
		System.out.println("join() 함수 호출됨!");
		System.out.println(dto);
		return "/"; // 정확하게 써줘도 된다.
	}
	
	@RequestMapping("/user/login")
	public String login(LoginDto dto) {
		System.out.println("login() 함수 호출됨!");
		System.out.println(dto);
		return "/";
	}
	
	@RequestMapping("/user")
	public String user() {
		System.out.println("user() 함수 호출됨!");
		return "/";
	}
	
	@RequestMapping("/hello")
	public String hello() {
		System.out.println("hello() 함수 호출됨!");
		return "/";
	}
	
	@RequestMapping("/user/list")
	public String list(User user) {
		System.out.println("list() 함수 호출됨!");
		System.out.println(user);
		return "/";
	}
}
