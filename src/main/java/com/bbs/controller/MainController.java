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
import com.bbs.vo.Authmail;

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
	// 반환값을 페이지에 출력
	@ResponseBody
	public String sendAuthMail(String user_mail) throws Exception{
		
		int result = usersService.setAuthnum(user_mail);
		return result + "";
	}
	// url 패턴이 'path/mailAuth' 일 경우
	@RequestMapping(value="/mailAuth", method=RequestMethod.POST)
	// 반환값을 페이지에 출력
	@ResponseBody
	public String mailAuth(Authmail authmail) throws Exception{
		// String user_mail, String auth_num자리에
		// Authmail authmail로 받아와도 가능 대신 파라미터와 Authmail내에 프로퍼티명과 같아야함
		
		

		return null; // @ResponseBody가 없을 때 0이 들어가면 URL이 0인 곳으로 이동.
	}
	
}
