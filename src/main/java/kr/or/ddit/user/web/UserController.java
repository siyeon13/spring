package kr.or.ddit.user.web;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.model.UsersVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("user")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
 
	@Resource(name = "userService")
	private UserService userService;
	
	
	@RequestMapping("allUser")
	public String view(Model model) {
		List<UserVo> userList = userService.selectAllUser();
		model.addAttribute("userList", userList);
		return "user/allUser";
	}
	
	
	@RequestMapping("pagingUser")
	public String paigingUser(@RequestParam(defaultValue = "1") int page,
							  @RequestParam(defaultValue = "5") int pageSize,
							  Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		return "user/pagingUser";
	}
	
	
	//@RequestMapping("pagingUser")
	public String pagingUser(PageVo pageVo) {
		
		 logger.debug("pageVo : {}", pageVo);
		
		return "" ;
	}
	
	// 상세보기
	@RequestMapping("user")
	public String detailUser(Model model, String userid) {
		UserVo user = userService.selectUser(userid);
		model.addAttribute("user", user);
		return "user/user";
	}
	
	// 수정페이지
	@RequestMapping(path="userModify", method = {RequestMethod.GET})
	public String modifyUser(Model model, String userid) {
		UserVo user = userService.selectUser(userid);
		model.addAttribute("user", user);
		
//		usersVo = 
//				userService.modifyUser(userVo);

		
		return "user/userModify";
	}
	
	
	
	@RequestMapping(path="userModify", method = {RequestMethod.POST})
	//@RequestMapping("userModify2")
	public String modifyUser(Model model, UserVo userVo ) {
		logger.debug("userVo : {} ", userVo.getUserid());
		userVo.setRealfilename("");
		userVo.setFilename("");
		
		userService.modifyUser(userVo);
		
		return "redirect:/user/user?userid=" + userVo.getUserid();
//		return "user/pagingUser";
		}
		
	
	
	
	
	
	
	
	
	
}
