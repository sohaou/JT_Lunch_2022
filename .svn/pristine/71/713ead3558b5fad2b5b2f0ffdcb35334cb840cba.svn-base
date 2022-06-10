package egovframework.jtLunch.admin.dashboard.Controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import egovframework.jtLunch.admin.dashboard.DTO.NoticeDTO;
import egovframework.jtLunch.admin.dashboard.Service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	//관리자 공지사항
	@RequestMapping(value = "/admin/notice", method = RequestMethod.GET)
	public String notice(NoticeDTO noticedto, Model model) throws Exception {
		
		noticeService.CountSet();
		noticeService.CountUpdate(noticedto);
		List<NoticeDTO> dto = noticeService.NoticePrint();
		model.addAttribute("notice", dto);
		
		return "admin/notice/notice";
	}
		
	@RequestMapping(value = "/admin/noticeinsert", method = RequestMethod.GET)
	public String noticeinsert(Model model) throws Exception {
		
		return "admin/notice/noticeinsert";
	}
		
	//공지사항 등록
	@RequestMapping(value = "/admin/noticeInsert", method = RequestMethod.POST)
	public String noticeInsert(NoticeDTO dto, Model model) throws Exception {
		
		noticeService.NoticeInsert(dto);

		return "redirect:/admin/notice";
	}
	
	//공지사항 상세보기
	@RequestMapping(value = "/admin/noticedetails", method = RequestMethod.GET)
	public String noticedetails(@RequestParam("notice_id") int notice_id, Model model) throws Exception {

		model.addAttribute("notice", noticeService.NoticeSelect(notice_id));
		
		return "admin/notice/noticeDetails";
	}
	//공지사항 수정
	@RequestMapping(value = "/admin/noticeupdate", method = RequestMethod.GET)
	public String noticeupdate(@RequestParam("notice_id") int notice_id, Model model) throws Exception {
		
		model.addAttribute("notice", noticeService.NoticeSelect(notice_id));
		
		return "admin/notice/noticeupdate";
	}
	
	@RequestMapping(value = "/admin/noticeUpdate", method = RequestMethod.POST)
	public String noticeUpdate(NoticeDTO dto, HttpServletRequest re, Model model) throws Exception {
		
		noticeService.NoticeUpdate(dto);

		return "redirect:/admin/notice";
	}
	//공지사항 삭제
	@RequestMapping(value = "/admin/noticeDelete", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String noticeDelete(@RequestParam("notice_id") int notice_id, Model model) throws Exception {
		
		String resultMsg = "";
		
		try {
			noticeService.NoticeDelete(notice_id);
			resultMsg = "삭제완료";
		} catch(Exception e){
			resultMsg = "삭제실패";
		}
			
		return resultMsg;	
	}
}
