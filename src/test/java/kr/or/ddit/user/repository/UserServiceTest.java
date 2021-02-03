package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

public class UserServiceTest extends ModelTestConfig{
	
	@Resource(name = "userService")
	private UserService userService;

	@Test
	public void getUsertest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userService.selectUser(userid);
		
		/***Then***/
		assertEquals("브라운", userVo.getUsernm());

	}
	
	
	@Test
	public void selectAllUserTest() {

		/***Given***/
		UserVo userVo = new UserVo("testUser", "테스트사용자", "testUserPass", new Date(), "대덕", "대전 중구 중앙로 76", "4층",
				"34940","brown.png" ,"1234");
	
		/***When***/
		List<UserVo> userList = userService.selectAllUser();
		
		/***Then***/
		assertEquals(24, userList.size());
	}
	
	@Test
	public void selectUserNotExsistTest() {
		/***Given***/
		String userid = "browndfdfdse";
		/***When***/
		UserVo user = userService.selectUser(userid);
		
		/***Then***/
		assertNull(user);

	}
	@Test
	public void selectPagingUserTest() {
		
		/***Given***/
		PageVo pagevo = new PageVo(2, 5);

		/***When***/
		List<UserVo> userList = (List<UserVo>) userService.selectPagingUser(pagevo);
		
		
		/***Then***/
		assertEquals(5, userList.size());
		
	}
	
	@Test
	public void modifyUserCnt() {
		
		/***Given***/
		// userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit",	"대덕인재",	"dditpass", new Date(), "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940","brown.png" ,"12345");
		
		/***When***/
		int updateCnt = userService.modifyUser(userVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
		
	}
	

}
