package com.bbs.service;

import com.bbs.vo.Authmail;
import com.bbs.vo.Users;

// service를 구성할 interface 표준 설계 작성
public interface UsersService {
	
	public int idCheck(String user_id) throws Exception;
	public int setAuthnum(String user_mail) throws Exception;
	public int checkAuthnum(Authmail authmail) throws Exception; // 결과값 존재
	public void joinAction(Users users) throws Exception;// 반환타입 필요없음
	public int loginAction(Users users) throws Exception;

}
