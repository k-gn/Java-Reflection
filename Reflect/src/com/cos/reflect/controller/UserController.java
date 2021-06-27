package com.cos.reflect.controller;

import com.cos.reflect.anno.RequestMapping;

public class UserController {

	@RequestMapping("/join") // 해당 애노테이션의 value함수 값으로 들어간다.
	public String join() {
		System.out.println("join() 함수 호출됨!");
		return "/index.jsp"; // 정확하게 써줘도 된다.
	}
	
	@RequestMapping("/login")
	public String login() {
		System.out.println("login() 함수 호출됨!");
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
}
