package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service("userService")	// 이름을 지정해줘야 자식 컨테이너에서 불러올 수 있다
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
	public UserVo selectUser(String userid) {
		return userDao.selectUser(userid);
	}

// GET SET 생성
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	

	@Override
	public List<UserVo> selectAllUser() {
		// TODO Auto-generated method stub
		return userDao.selectAllUser();
	}

	@Override
	public Map<String, Object> selectPagingUser(PageVo pageVo) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("pageVo", pageVo);
		resultMap.put("userList", userDao.selectPagingUser(pageVo));
//		resultMap.put("userCnt", userDao.selectAllUserCnt());
		
		int userCnt = userDao.selectAllUserCnt();
		resultMap.put("pagination" , (int)Math.ceil( (double)userCnt /pageVo.getPageSize() ));
//		resultMap.put("pagination" , (int)Math.ceil(  Double.valueOf(resultMap.get("userCnt").toString()) /pageVo.getPageSize() ));
		
		return resultMap;
	}

	@Override
	public int modifyUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return userDao.modifyUser(userVo);
	}


	@Override
	public int insertUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return userDao.insertUser(userVo);
	}


	@Override
	public int deleteUser(String userid) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(userid);
	}

}
