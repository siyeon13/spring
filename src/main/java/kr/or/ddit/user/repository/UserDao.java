package kr.or.ddit.user.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

@Repository
public interface UserDao {

	// 사용자 아이디로 사용자 조회
	UserVo selectUser(String userid);
	
	List<UserVo> selectAllUser();	
	
	List<UserVo> selectPagingUser(PageVo pagevo); 



	int selectAllUserCnt() ;


	int modifyUser(UserVo userVo); 


	int insertUser(UserVo userVo) ;


	int deleteUser(String userid) ;

	
}
