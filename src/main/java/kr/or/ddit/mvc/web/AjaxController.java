package kr.or.ddit.mvc.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.or.ddit.user.model.UserVo;

@RequestMapping("ajax")
@Controller
public class AjaxController {

	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

	
	// request mapping 실행하기 전에 먼저 실행하고 model 객체 반환한다
	@ModelAttribute(name = "rangers")
	public List<String> rangers() {
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("cony");
		rangers.add("james");
		rangers.add("moon");
		
		return rangers;
	}
	
	
	@RequestMapping("view")
	public String view() {
		return "ajax/ajaxView";
	}
	
	@RequestMapping("form")
	public String form(UserVo userVo) {
		logger.debug("userVo : " , userVo);
		
		return "jsonView";
		
	}
	
	
	// json 응답을 만들어내는 것 
	// localhost/ajax/jsonView
	@RequestMapping("jsonView")
	public String jsonView(Model model) {
//		List<String> rangers = new ArrayList<String>();
//		rangers.add("brown");
//		rangers.add("sally");
//		rangers.add("cony");
//		rangers.add("james");
//		rangers.add("moon");
//		
//		model.addAttribute("rangers", rangers);
		
		return "jsonView";
	}
	
	@RequestMapping("jsonViewObj")
	public View jsonViewViewObj() {
		return new MappingJackson2JsonView();
	}
	
	@RequestMapping("jsonViewMav")
	public ModelAndView jsonViewMav() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");
		return mav;
	}
	
	//위에서 만든 메소드가 아래에 있는 메소드와 연결이 된다.
}
