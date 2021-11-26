package com.bbs.service;

import java.util.Properties;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.bbs.DAO.UsersDAO;
import com.bbs.util.Mail;
import com.bbs.vo.Authmail;

@Service // 구현하는 서비스 위에
public class UsersServiceImpl implements UsersService {

	@Inject
	UsersDAO dao;
	
	@Override
	public  int idCheck(String user_id) throws Exception {
		
		int result = 0;
		if(dao.idCheck(user_id) != null) result = 1;

		return result;

	}

	@Override
	public int setAuthnum(String user_mail) throws Exception {
		
		int result = 0;
		
		Random rd = new Random();
		
		int auth_num = rd.nextInt(9999) + 1; // 1부터 9999까지
		
		String from = "zerogod0703@gmail.com";
		String to	= user_mail;
		String subject = "인증번호 메일";
		String content = "다음 인증번호를 입력하세요. <br> <h2>" + auth_num + "</h2>";
		
		Integer exist = dao.getAuthnum(to);
		
		if(exist != null)dao.setAuthnum(new Authmail(to, auth_num)); // 주소생성 후 바로 넘겨주기
					else dao.resetAuthnum(new Authmail(to, auth_num));
			
		
		
		Properties p = new Properties();
		
		//환경설정 맞추기
		p.put("mail.smtp.user", from); // 환경설정을 from으로 
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth","true");
		p.put("mail.smtp.debug","true");
		p.put("mail.smtp.socketFactory.port", "587");
		p.put("mail.smtp.socketFactory.fallback","false");
		p.put("mail.smtp.ssl.trust","smtp.gmail.com");
		p.put("mail.smtp.ssl.protocols","TLSv1.2");
		
		
		try {
			
			Authenticator auth = new Mail(); // 로그인
			Session s = Session.getInstance(p, auth); // 세션 받아옴
			s.setDebug(true); // 디버깅
			
			MimeMessage msg = new MimeMessage(s); // 세션을 이용해 완경 맞추기
			
			// Address로 받아오기
			Address fromAddr = new InternetAddress(from);
			Address toAddr = new InternetAddress(to);
			
			// Address로 받아온 거 msg에 입력
			msg.setSubject(subject);
			msg.setRecipient(Message.RecipientType.TO, toAddr);
			msg.setFrom(fromAddr);
			msg.setContent(content, "text/html; charset=UTF-8");
			
			Transport.send(msg); // 모두 작성 후 보냄
			
		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}

}
