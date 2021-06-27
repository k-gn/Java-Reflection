package com.cos.reflect.filter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.reflect.anno.RequestMapping;
import com.cos.reflect.controller.UserController;

public class Dispatcher implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("디스패쳐 진입");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		System.out.println("컨텍스트 패스 : " + req.getContextPath());
		System.out.println("식별자 주소 : " + req.getRequestURI());
		System.out.println("전체 주소 : " + req.getRequestURL());

		String endPoint = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("endPoint : " + endPoint);

		UserController controller = new UserController();

		Method[] methods = controller.getClass().getDeclaredMethods(); // 해당 파일의 메서드만 가져오기

		// 리플렉션 -> 메서드를 런타임 시점에서 찾아 실행
//		for (Method method : methods) {
//			System.out.println(method.getName());
//			if (endPoint.equals("/" + method.getName())) {
//				try {
//					method.invoke(controller);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
		for(Method method : methods) {
			Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class); // 해당 어노테이션을 찾는다.
			RequestMapping requestMapping = (RequestMapping) annotation;
			System.out.println(requestMapping.value());
			
			if(requestMapping.value().equals(endPoint)) {
				try {
					Parameter[] params = method.getParameters();
					String path = "";
					if(params.length != 0) {
						System.out.println("params[0] : " + params[0]);
						System.out.println("params[0].getName() : " + params[0].getName());
						System.out.println("params[0].getType() : " + params[0].getType());
						Object dtoInstance = params[0].getType().newInstance(); // 파라미터 객체 생성
//						String username = request.getParameter("username");
//						String password = request.getParameter("password");
//						System.out.println("username : " + username);
//						System.out.println("password : " + password);
						Enumeration<String> keys = request.getParameterNames(); // key 값들
						
					}else {
						path = (String) method.invoke(controller);
					}
					RequestDispatcher rd = request.getRequestDispatcher(path); // 현재 '/' 경로니까 알아서 웰컴페이지(index.jsp) 를 찾는다. ( / = /index.jsp )
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}

	}

}
