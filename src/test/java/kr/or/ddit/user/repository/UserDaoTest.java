package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

//스프링 환경에서 junit 코드를 실행 ==> junit 코드도 스프링 빈으로 등록 
public class UserDaoTest extends ModelTestConfig{

	// 주입하고 싶은것 입력
	@Resource(name="userDao")
	private UserDao userDao;

	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userDao.selectUser(userid);
		
		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
	}
	
	
	@Test
	public void selectAllUserTest() {

		/***Given***/
		
		/***When***/
		// List<UserVo> userList = userService.selectAllUser();
		int userCnt = userDao.selectAllUserCnt();
		/***Then***/
		assertEquals(25, userCnt);
		//데이터 건수를 이용해서 일치하는지 확인
	}
	
	
	@Test
	public void selectPagingUserTest() {
		
		/***Given***/
		PageVo pagevo = new PageVo(2, 5);

		/***When***/
		List<UserVo> userList = userDao.selectPagingUser(pagevo);
		
		
		/***Then***/
		//데이터 건수를 이용해서 일치하는지 확인
		assertEquals(5, userList.size());
		
	}
	
	@Test
	public void selectAllUserCnt() {
		
		/***Given***/
		
		/***When***/
		int userCnt = userDao.selectAllUserCnt();
		
		/***Then***/
		//데이터 건수를 이용해서 일치하는지 확인
		assertEquals(25, userCnt);
		
	}
	
	@Test
	public void modifyUserCnt() {
		
		/***Given***/
		// userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit",	"대덕인재",	"dditpass", new Date(), "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940","brown.png" ,"12345");
		
		/***When***/
		int updateCnt = userDao.modifyUser(userVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
		
	}
	
	@Test
	public void registUserTest() {
		
		/***Given***/
		// userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("77777",	"77777",	"dditpass77", new Date(), "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940","brown.png" ,"12345");
		
		/***When***/
		int updateCnt = userDao.insertUser(userVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
	
	
	}			
	// 삭제 테스트
	@Test
	public void deleteUserTest() {
		/***Given***/
		// 해당 테스트가 실행될 때는 testUser라는 사용자가 before 메소드에 의해 등록이 된 상태
		String userid = "77777";

		/***When***/
		int deleteCnt = userDao.deleteUser(userid);

		/***Then***/
		assertEquals(1, deleteCnt);

	}
	
	
	
	
	

}
