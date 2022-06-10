package egovframework.jtLunch.main.notice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.jtLunch.main.notice.Service.UserNoticeService;

@Controller
public class UserNoticeController {

	@Autowired
	private UserNoticeService usernoticeService;
	
	@GetMapping("/user/notice")
	public String OwnerNotice(Model model) throws Exception {
		model.addAttribute("noticeAll", usernoticeService.UserNoticePrint());
		
		return "user/UserNotice";
	}
	
		@RequestMapping(value = "/user/noticeDetail", method = RequestMethod.GET)
		public String NoticeDetail(@RequestParam("id") int notice_id, Model model) throws Exception{
			
			model.addAttribute("noticeDetail", usernoticeService.UserNoticeDetail(notice_id));
			usernoticeService.ViewUpdate(notice_id);
			
			return "user/UserNoticeDetail";
		}
}
