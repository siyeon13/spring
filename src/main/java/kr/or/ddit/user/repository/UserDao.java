package kr.or.ddit.user.repository;

import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;

@Repository
public interface UserDao {

	// 사용자 아이디로 사용자 조회
	UserVo getUser(String userid);
	
	
	
}
