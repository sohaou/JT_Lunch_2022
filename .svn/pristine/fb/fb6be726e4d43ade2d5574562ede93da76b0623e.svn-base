package egovframework.jtLunch.main.request.Controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.jtLunch.main.request.DTO.UserRequestDTO;
import egovframework.jtLunch.main.request.Service.UserRequestService;

@Controller
public class UserRequestController {

	@Autowired
	private UserRequestService userRequestService;
	
	//사용자 요청사항 페이지
	@GetMapping("/user/request")
	public String UserRequest(Model model) throws Exception {
		model.addAttribute("requestAll", userRequestService.UserRequestPrintAll());
		return "user/UserRequest";
	}
	
	//사용자 요청사항 등록 페이지로 이동
	@GetMapping("/user/requestInsertForm")
	public String requestInsertForm(Model model, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		//PrintWriter out=response.getWriter();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String user_id = authentication.getName();
		model.addAttribute("UserId",userRequestService.UserLoginName(user_id));
		return "user/UserRequestInsert";
	}
	
	//사용자 요청사항 등록 저장
	@RequestMapping(value = "/user/requestSave", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String requestSave(UserRequestDTO dto){
		String msg = "";
		try {
			userRequestService.UserRequestInsert(dto);
			msg = "등록 완료";
		} catch (Exception e) {
			msg = "등록 실패";
		}
		return msg;
	}
	
	//식당운영자 공지사항 상세보기
	@RequestMapping(value = "/user/requestDetail", method = RequestMethod.GET)
	public String NoticeDetail(@RequestParam("id") int req_id, Model model,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		//PrintWriter out=response.getWriter();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String user_id = authentication.getName();
		model.addAttribute("UserId",userRequestService.UserLoginName(user_id));
		model.addAttribute("requestDetail", userRequestService.UserRequestDetail(req_id));
		
		return "user/UserRequestDetail";
	}
	
	//식당운영자 공지사항 수정할 정보 가져오기
	@RequestMapping(value = "/user/requestModi", method = RequestMethod.GET)
	public String noticeModi(@RequestParam("id") int req_id, Model model) throws Exception{
		model.addAttribute("requestDetail", userRequestService.UserRequestDetail(req_id));
			
		return "user/UserRequestUpdate";
	}
	
	//식당운영자 공지사항 수정 저장
	@RequestMapping(value = "/user/requestUpdate", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String noticeUpdate(UserRequestDTO dto){
		String msg="";
		try {
			userRequestService.UserRequestUpdate(dto);
			msg=" 수정 완료";
		} catch (Exception e) {
			msg="수정 실패";
		}
		return msg;
	}
	
	//식당운영자 공지사항 삭제
	@RequestMapping(value = "/user/requestDelete", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String noticeDelete(@RequestParam("req_id") int req_id){
		String msg="";
		try {
			userRequestService.UserRequestDelete(req_id);
			userRequestService.UserCountSet();
			userRequestService.UserCountUpdate();

			msg="삭제 완료";
		} catch (Exception e) {
			msg="삭제 실패";
		}
		return msg;
	}
}
