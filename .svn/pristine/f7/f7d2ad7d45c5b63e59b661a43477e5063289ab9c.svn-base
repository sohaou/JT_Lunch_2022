package egovframework.jtLunch.cmmn.security.Controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.jtLunch.cmmn.security.Service.UserService;

@Controller
public class LoginController {
	
	@Inject
	UserService service;
	
	@RequestMapping(value="/loginform")
	public String mainPage() {
		return "/main/login/index";
	}
	
	@RequestMapping(value="/adminLoginForm")
	public String adminLoginForm() {
		return "/admin/login/adminLogin";
	}
	
	@RequestMapping(value="/ownerLoginForm")
	public String ownerLoginForm() {
		return "/admin/login/ownerLogin";
	}
	
	@RequestMapping(value="/access_denied")
	public String access_denied() {
		return "/cmmn/403-error";
	}
}
