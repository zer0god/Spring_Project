package com.bbs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bbs.service.BbsService;
import com.bbs.service.UsersService;
import com.bbs.vo.Authmail;
import com.bbs.vo.Users;

// url 전송 : http://localhost:8081/

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	// 객체 생성
	@Inject
	UsersService usersService;
	@Inject
	BbsService bbsService;
	
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
	

	// url 패턴이 'path/bbs' 일 경우
	@RequestMapping(value = "/bbs", method = RequestMethod.GET)
	public String bbs(Integer pageNumber, Model model) throws Exception {
		
		if(pageNumber == null) pageNumber = 1;
		
		model.addAttribute("map", bbsService.bbs(pageNumber));
		
		return "bbs/bbs";
	}
	
	// url 패턴이 'path/joinAction' 일 경우
	@RequestMapping(value ="/joinAction", method=RequestMethod.POST)
	public String joinAction(Users users, String addr1, String addr2, String addr3) throws Exception{
		
		users.setUser_addr(addr1 + " " + addr2 + " " + addr3);
		usersService.joinAction(users);
		
		return "redirect:/login";
		// 보이는 url /joinAction 실제는 return값의 url
		// 회원가입 된 상태에서는 url 변경해줘야하므로 redirect
		// redirect:/login == http://localhost:8081/loign
	}
	
	@RequestMapping(value="/loginAction", method=RequestMethod.POST)
	// 결과값 필요없으므로 responsebody 필요없음
	public String loginAction(Users users, HttpSession session, RedirectAttributes ra) throws Exception{
		
		int result = usersService.loginAction(users);
		String url = null;
		
		if(result == 0) {
			session.setAttribute("user_id", users.getUser_id());
			// 페이지 이동 -> localhost:8081/
			url = "redirect:/";
		}
		else {
			// 메세지를 전달 (로그인 정보가 잘못되었습니다.)
			// 페이지 이동 -> localhost:8081/login
			ra.addFlashAttribute("msg", "로그인 정보가 일치하지 않습니다.");
			return "redirect:/login";
		}
		
		return url;
	}
	
	// url 패턴이 'path/logout' 일 경우
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		
		session.invalidate();
		
		return "redirect:/";
		
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
		
		return usersService.checkAuthnum(authmail) + ""; 
		// @ResponseBody가 없을 때 0이 들어가면 URL이 0인 곳으로 이동.
		// 		int result = usersService.setAuthnum(user_mail);
		//		return result + ""; 와 같은 것임
	}
	
	
}
