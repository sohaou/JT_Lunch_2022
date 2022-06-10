package egovframework.jtLunch.admin.owner.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.jtLunch.admin.cmmn.Service.CmmnRequestService;
import egovframework.jtLunch.admin.dashboard.DTO.NoticeDTO;
import egovframework.jtLunch.admin.owner.Service.OwnerNoticeService;

@Controller
public class OwnerBoardController {

	@Autowired
	private CmmnRequestService cmmnrequestService;
	@Autowired
	private OwnerNoticeService ownerNoticeService;
	
	//식당운영자 공지사항 페이지
	@GetMapping("/owner/notice")
	public String OwnerNotice(Model model) throws Exception {
		model.addAttribute("noticeAll", ownerNoticeService.OwnerNoticePrint());
		return "admin/owner/notice/ownerNotice";
	}
	
	//식당운영자 공지사항 등록 페이지로 이동
	@GetMapping("/owner/noticeInsertForm")
	public String noticeInsertForm() {
		return "admin/owner/notice/ownerNoticeInsert";
	}
	
	//식당운영자 공지사항 등록 저장
	@RequestMapping(value = "/owner/noticeSave", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String noticeSave(NoticeDTO dto){
		String msg = "";
		try {
			ownerNoticeService.OwnerNoticeInsert(dto);
			msg = "등록 완료";
		} catch (Exception e) {
			msg = "등록 실패";
		}
		return msg;
	}
	
	//식당운영자 공지사항 상세보기
	@RequestMapping(value = "/owner/noticeDetail", method = RequestMethod.GET)
	public String NoticeDetail(@RequestParam("id") int notice_id, Model model) throws Exception{
		model.addAttribute("noticeDetail", ownerNoticeService.OwnerNoticeDetail(notice_id));
		
		return "admin/owner/notice/ownerNoticeDetail";
	}
	
	//식당운영자 공지사항 수정할 정보 가져오기
	@RequestMapping(value = "/owner/noticeModi", method = RequestMethod.GET)
	public String noticeModi(@RequestParam("id") int notice_id, Model model) throws Exception{
		model.addAttribute("noticeDetail", ownerNoticeService.OwnerNoticeDetail(notice_id));
			
		return "admin/owner/notice/ownerNoticeModi";
	}
	
	//식당운영자 공지사항 수정 저장
	@RequestMapping(value = "/owner/noticeUpdate", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String noticeUpdate(NoticeDTO dto){
		String msg="";
		try {
			ownerNoticeService.OwnerNoticeUpdate(dto);
			msg=" 수정 완료";
		} catch (Exception e) {
			msg="수정 실패";
		}
		return msg;
	}
	
	//식당운영자 공지사항 삭제
	@RequestMapping(value = "/owner/noticeDelete", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String noticeDelete(@RequestParam("id") int notice_id){
		String msg="";
		try {
			ownerNoticeService.OwnerNoticeDelete(notice_id);
			ownerNoticeService.OwnerCountSet();
			ownerNoticeService.OwnerCountUpdate();
			
			msg="삭제 완료";
		} catch (Exception e) {
			msg="삭제 실패";
		}
		return msg;
	}
	
	//식당운영자 _ 요청사항게시판이동
	@GetMapping("/owner/request")
	public String OwnerRequestPage(Model model) throws Exception{
		model.addAttribute("requestAll", cmmnrequestService.printRequestAll());
		
		return "admin/owner/notice/ownerRequest";
	}
	
	//식당운영자 _ 요청사항상세보기
	@RequestMapping(value = "/owner/requestDetail", method = RequestMethod.GET)
	public String requestDetail(@RequestParam("reqid") String req_id,  Model model) throws Exception{
		model.addAttribute("requestDetail", cmmnrequestService.selectRequest(req_id));
		cmmnrequestService.ownerCheck(req_id);
			
		return "admin/owner/notice/ownerRequestDetail";
	}
}
