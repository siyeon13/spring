package kr.or.ddit.user.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

// <bean id="" class=""
// @Repository에서 별다른 설정을 하지 않으면 스프링 빈 이름으로 class 이름에서 첫글자를 소문자로 한
// 문자열이 스프링 빈의 이름으로 설정된다
// UserDaoImpl ==> userDaoImpl

// UserDao/ UserDaoImpl ==> @Resource(name="userDaoImpl")
// UserDaoI / UserDao ==> @Resource(name="userDao")

@Repository("userDao")		// <-- 이름을 직접 넣어주는 방법
public class UserDaoImpl implements UserDao{

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	/*
	@Override
	public UserVo selectUser(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		// 메소드로 들어온 인자
		UserVo user = sqlSession.selectOne("users.selectUser", userid);
		sqlSession.close();
		
		return user;
	}
	*/
	
	@Override
	public UserVo selectUser(String userid) {
		
		// 원래는 데이터베이스에서 조회를 해야하나, 개발 초기단계라
		// 설정이 완료되지 않음, 현재 확인하려고 하는 기능은 스프링 컨테이너에 초점을 맞추기 위해
		// new 연산자를 통해 생성한 vo 객체를 반환
		
		//return new UserVo("brown", "브라운","brownPass");
		return template.selectOne("users.selectUser" , userid);
	}

	@Override
	public List<UserVo> selectAllUser() {
		// TODO Auto-generated method stub
		return template.selectList("users.selectAllUser");
	}

	@Override
	public List<UserVo> selectPagingUser(PageVo pagevo) {
		// TODO Auto-generated method stub
		return template.selectList("users.selectPagingUser",pagevo);
	}

	@Override
	public int selectAllUserCnt() {
		// TODO Auto-generated method stub
		return template.selectOne("users.selectAllUserCnt");
	}

	@Override
	public int modifyUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return template.update("users.modifyUser", userVo);
	}

	@Override
	public int insertUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return template.insert("users.insertUser", userVo);
	}

	@Override
	public int deleteUser(String userid) {
		// TODO Auto-generated method stub
		return template.delete("users.deleteUser", userid);
	}

	
}
