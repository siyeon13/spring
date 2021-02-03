package kr.or.ddit.hello;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

/*
 * java - gui swing, awt, java fx
 */


public class HelloControllerTest extends WebTestConfig{

	//@Resource(name = "helloController")
	// 스프링빈중에 대입 가능한 타입의 스프링 빈을 주입한다
	// 만약 동일한 타입의 스프링 빈이 여러개 있을 경우 @Qulifier 어노테이션을 통해
	// 특정 스프링 빈의 이름을 지칭할 수 있다
	// ==> @Resource(name = "userService")


	//localhost/hello/view
	@Test
	public void viewTest() throws Exception {
		//코드 한 라인으로 작성 mock
		MvcResult mvcResult = mockMvc.perform(get("/hello/view"))
								.andExpect(status().isOk())
								.andExpect(view().name("hello"))
								.andExpect(model().attributeExists("userVo"))
								.andDo(print())
								.andReturn();
		//isOk() : 응답이 정상적으로 옴 200에러
		
		ModelAndView mav = mvcResult.getModelAndView();
		
//		assertEquals("기대값", "실제값");
		assertEquals("hello", mav.getViewName());
		UserVo userVo = (UserVo)mav.getModel().get("userVo");
		assertEquals("브라운", userVo.getUsernm());
		
	}
	
	
	@Test
	public void pahtVariableTest() throws Exception{
		//코드 한 라인으로 작성 mock
		MvcResult mvcResult = mockMvc.perform(get("/hello/path/brown"))
								.andExpect(status().isOk())
								.andExpect(model().attributeExists("subpath"))
								.andDo(print())
								.andReturn();
	}
	

}














