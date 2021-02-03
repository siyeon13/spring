package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.model.UsersVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.validator.UserVoValidator;

@RequestMapping("user")
@Controller
public class UserController {
	
	// 길이제한 
//	if(userVo.getUserid().length() < 5) {}
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
 
	@Resource(name = "userService")
	private UserService userService;
	
	
	@RequestMapping("allUser")
	public String view(Model model) {
		List<UserVo> userList = userService.selectAllUser();
		model.addAttribute("userList", userList);
		return "user/allUser";
	}
	
	@RequestMapping("allUserTiles")
	public String allUserTiles(Model model) {
		List<UserVo> userList = userService.selectAllUser();
		model.addAttribute("userList", userList);
		return "tiles.user.allUser";
	}
	
	
	@RequestMapping("pagingUser")
	public String paigingUser(@RequestParam(defaultValue = "1") int page,
							  @RequestParam(defaultValue = "5") int pageSize,
							  Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		return "user/pagingUser";
	}
	

	@RequestMapping("pagingUserTiles")
	public String paigingUserTiles(@RequestParam(defaultValue = "1") int page,
							  @RequestParam(defaultValue = "5") int pageSize,
							  Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		// tiles-definition 에 설정한 name
		return "tiles.user.pagingUser";
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

//		return "user/user";
		return "tiles.user.user";
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
		
	
	@RequestMapping(path="regist", method=RequestMethod.GET)
	public String regist() {
		return "user/registUser";
	}
	
	
//	@RequestMapping(path="regist", method = {RequestMethod.POST})
//	public String regist(UserVo userVo, MultipartFile profile, Model model, BindingResult result) {
//		userVo.setRealfilename("");
//		userVo.setFilename("");
//		
//		new UserVoValidator().validate(userVo, result);
//		
//		if(result.hasErrors()) {
//			logger.debug("result has error");
//			return "user/registUser";
//		}
//		
//		int registCnt = 0;
//		
//		registCnt = userService.insertUser(userVo);
//		
//		return "user/pagingUser";
//	}
	
	
	//bindingResult 객체는 command 객체 바로 뒤에 인자로 기술해야 한다
		@RequestMapping(path="regist", method=RequestMethod.POST)
		public String regist(@Valid UserVo userVo, BindingResult result, MultipartFile profile, Model model) {
		
		//	new UserVoValidator().validate(userVo, result);
			
			if(result.hasErrors()) {
				logger.debug("result has error");
				return "user/registUser";
			}
			
			int insertCnt = 0;
			String originalFilename = ""; 
			String filename = "";
			
			if(profile.getSize() > 0) {
				originalFilename = profile.getOriginalFilename();
				filename = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
				
				userVo.setFilename(originalFilename);
				userVo.setRealfilename("d:\\upload\\" + filename);
				
				try {
					profile.transferTo(new File(userVo.getRealfilename()));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
			insertCnt = userService.insertUser(userVo);
					
			
			//사용자 등록이 정상적으로 된 경우	==> 사용자 페이징 리스트로 이동(1페이지)
			if(insertCnt == 1) {
				return "redirect:/user/pagingUser";
			}
			//사용자 수정이 비정상적으로 된 경우 ==> 사용자 등록 페이지로 이동(사용자가 입력한 값 설정)
			else {
				return "user/registUser";
			}
		}
	
		@RequestMapping("deleteUser")
		public String deleteUser(String userid) {
			int deleteCnt = 0;
			deleteCnt = userService.deleteUser(userid);
			return "redirect:/user/pagingUser";
			
		}
	
		
		@RequestMapping("excelDownload")
		public String excelDownload(Model model) {
			List<String> header = new ArrayList<String>();
			header.add("사용자 아이디");
			header.add("사용자 이름");
			header.add("사용자 별명");
			
			List<UserVo> data = new ArrayList<UserVo>();
			
			
			model.addAttribute("header", header);
			model.addAttribute("data", userService.selectAllUser());
			
			return "userExcelDownloadView";
		}
		
		
		
		
		
		//localhost/user/profile
		@RequestMapping("profile")
		public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) { 
			resp.setContentType("image");
			
			// userid 파라미터를 이용하여
			// usrService 객체를 통해 사용자의 사진 파일 이름을 획득
			// 파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream으로 응답 생성
			
			UserVo userVo = userService.selectUser(userid);
			
			String path = "";
			if(userVo.getRealfilename() == null) {
				path = req.getServletContext().getRealPath("/image/unknown.png");
			}
			else {
				path= userVo.getRealfilename();
			}
			
			logger.debug("path : {}" , path);
			
			try {
				FileInputStream fis = new FileInputStream(path);
				ServletOutputStream sos = resp.getOutputStream();
				
				byte[] buff = new byte[512];
				while(fis.read(buff) != -1) {
					sos.write(buff);
					
				}
				
				fis.close();
				sos.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		@RequestMapping("profileDownload")
		public void profileDownload(String userid, UserVo userVo, HttpServletResponse resp, HttpServletRequest req) {
			
			userVo = userService.selectUser(userid);
			
			String path = "";
			resp.setHeader("Content-Disposition", "attachment; filename=" + userVo.getFilename());
			if(userVo.getRealfilename()!=null) {
				path= "d:\\upload\\" + userVo.getRealfilename();
			}
			else {
				path = req.getServletContext().getRealPath("/image/unknown.png");
			}
			
			// userid 파라미터를 이용하여
			// usrService 객체를 통해 사용자의 사진 파일 이름을 획득
			// 파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream으로 응답 생성
			
			
			logger.debug("path : {}" , path);
			
			try {
				FileInputStream fis = new FileInputStream(path);
				ServletOutputStream sos = resp.getOutputStream();
				
				byte[] buff = new byte[512];
				while(fis.read(buff) != -1) {
					sos.write(buff);
					
				}
				fis.close();
				sos.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
	
}

















