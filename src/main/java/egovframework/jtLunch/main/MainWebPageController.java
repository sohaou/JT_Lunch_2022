package egovframework.jtLunch.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainWebPageController {
	
	@RequestMapping(value="/")
	public String MainWebPage() throws Exception {
		
		return "redirect:/user/menuplanner";
	}
}
