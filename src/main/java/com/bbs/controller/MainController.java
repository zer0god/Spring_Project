package com.bbs.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bbs.service.UsersService;

// url 전송 : http://localhost:8081/

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	// 경로 찾아가기
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		// 작업
		
		// views 폴더의 "main", 만약 views/main/main이면 "main/main"
		return "main/main";
	}
	
}
