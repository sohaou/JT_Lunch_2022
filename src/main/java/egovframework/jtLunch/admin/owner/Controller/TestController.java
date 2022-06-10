package egovframework.jtLunch.admin.owner.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.jtLunch.admin.cmmn.UseDateData;

@Controller
public class TestController {

	
	@RequestMapping(value = "/owner/apiTest", method=RequestMethod.GET)
	public void test() throws Exception{
		UseDateData dd = new UseDateData();
		String dietDate = dd.randomDietDate();
		
		System.out.println(">>>>>>>>>>>>>>" + dietDate);

	}
}
