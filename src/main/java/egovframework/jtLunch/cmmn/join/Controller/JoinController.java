package egovframework.jtLunch.cmmn.join.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.jtLunch.cmmn.join.Service.JoinService;
import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;


@Controller
public class JoinController {

	@Inject
	JoinService service;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
	@RequestMapping(value="/registerform")
	public String registerform() throws Exception{
		return "main/login/join";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register(CustomUserDetails customuserdetails) throws Exception {
		
		String PASSWORD = pwdEncoder.encode(customuserdetails.getPassword());
		customuserdetails.setPassword(PASSWORD);
		
		service.register(customuserdetails);
		System.out.println(customuserdetails);
		return "redirect:/loginform";
	}
	
	@RequestMapping(value="/checkID", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView checkID(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView("jsonView");
		String user_id = request.getParameter("user_id").toString();
		
		System.out.println("======================="+user_id);
		
		Map<String, Object> requestMap = new HashMap<String, Object>();
		
		requestMap.put("user_id", user_id);
		
		mv.addObject("checkID", service.checkID(requestMap));
		
		System.out.println("==========================="+mv);
		return mv;
		
	}
}
