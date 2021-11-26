package com.bbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbs.service.UsersService;

// url 전송 : http://localhost:8081/

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	// 객체 생성
	@Inject
	UsersService usersService;
	
	// 경로 찾아가기
	// url 패턴이 'path/' 일 경우
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) throws Exception {
		// 작업

		// views 폴더의 "main", 만약 views/main/main이면 "main/main"
		return "main/main";
	}
	// 경로 찾아가기
	// url 패턴이 'path/ioin' 일 경우
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) throws Exception {
		// 작업
		
		// views 폴더의 "main", 만약 views/main/main이면 "main/main"
		return "main/join";
	}
	// url 패턴이 'path/login' 일 경우
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) throws Exception {
		// 작업
		
		// views 폴더의 "main", 만약 views/main/main이면 "main/main"
		return "main/login";
	}
	// url 패턴이 'path/idCheck' 일 경우
	// http://localhost:8081/idCheck?user_id=qwer
	@RequestMapping(value ="/idCheck", method = RequestMethod.GET)
	// 반환값을 페이지에 출력
	@ResponseBody
	public String idCheck(String user_id) throws Exception{ // parameter
	
		int result = usersService.idCheck(user_id);
		return result + ""; // 문자열 전환
		
	}
	// url 패턴이 'path/sendAuthMail' 일 경우
	@RequestMapping(value="/sendAuthMail", method = RequestMethod.GET)
	@ResponseBody
	public String sendAuthMail(String user_mail) throws Exception{
		
		int result = usersService.setAuthnum(user_mail);
		return result + "";
	}
	
	
}
