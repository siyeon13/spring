package kr.or.ddit.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{

	// 주입
	@Resource(name="userDao")
	private UserDao userDao;		// null에러 나는 이유 ; 선언만 해줌 
	

	// 기본생성자 만들어 줘야 한다
	public UserServiceImpl() {
		super();
	}


	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	
	@Override
	public UserVo getUser(String userid) {
		return userDao.getUser(userid);
	}

// GET SET 생성
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
