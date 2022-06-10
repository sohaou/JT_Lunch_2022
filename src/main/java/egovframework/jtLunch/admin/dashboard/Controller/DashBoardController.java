package egovframework.jtLunch.admin.dashboard.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.jtLunch.admin.cmmn.Service.CmmnRequestService;
import egovframework.jtLunch.admin.dashboard.DTO.DashBoardDTO;
import egovframework.jtLunch.admin.dashboard.Service.DashBoardService;
import egovframework.jtLunch.admin.dashboard.Util.WeekDayUtil;
import egovframework.jtLunch.admin.owner.Service.OwnerMenuPlanService;
import egovframework.jtLunch.cmmn.join.Service.JoinService;

@Controller
public class DashBoardController {
	
	@Autowired
	private JoinService service;
	@Autowired
	private DashBoardService dashBoardService;
	@Autowired
	private OwnerMenuPlanService ownerMenuPlanService;
	@Autowired
	private CmmnRequestService cmmnrequestService;

	//관리자 main
	@GetMapping("/admin/main")
	public String DashBoardPage(Model model) throws Exception {
		
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) +1;
		int date = cal.get(Calendar.DATE);
		
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
		String strNowDate = simpleDateFormat.format(nowDate);
		
		model.addAttribute("reserveCount", ownerMenuPlanService.countReserve());
		model.addAttribute("ate_user", ownerMenuPlanService.countAte_user());
		model.addAttribute("month", strNowDate);
		model.addAttribute("money", 7000);
		model.addAttribute("weekCnt", WeekDayUtil.getCurrentWeekOfMonth(year,month,date));
		model.addAttribute("chartData", dashBoardService.getChartData());
		model.addAttribute("chartMData", dashBoardService.getChartMonth());
		model.addAttribute("monthate", dashBoardService.getmonthAte());
		model.addAttribute("searchUser", dashBoardService.searchUser());
		
		return "admin/adminMain";
	}
	
	//사용자 등록
	@RequestMapping(value = "/admin/userinsert", method = RequestMethod.GET)
	public String userinsert(DashBoardDTO dto, Model model) throws Exception {
		
		dashBoardService.userInsert(dto);
		
		return "redirect:/admin/main";
	}
	
	//사용자 ID 중복확인
	@RequestMapping(value="/checkid", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView checkid(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView("jsonView");
		String user_id = request.getParameter("user_id").toString();
		
		Map<String, Object> requestMap = new HashMap<String, Object>();
		
		requestMap.put("user_id", user_id);
		mv.addObject("checkid", service.checkID(requestMap));
		
		return mv;
	}
	
	//사용자 정보 삭제
	@RequestMapping(value = "/admin/userDelete", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String delete(@RequestParam("user_id") String user_id, Model model) {
		
		String resultMsg = "";
		
		try {	
			dashBoardService.deleteUser(user_id);	
			resultMsg = "삭제완료";
		} catch(Exception e){
			resultMsg = "삭제실패";
		}
			
		return resultMsg;	
	}

	//관리자 요청사항
	@RequestMapping(value = "/admin/request", method = RequestMethod.GET)
	public String request(Model model) throws Exception {
		
		model.addAttribute("request", cmmnrequestService.printRequestAll());
		
		return "admin/notice/request";
	}
	
	//요청사항 상세보기
	@RequestMapping(value = "/admin/requestdetails", method = RequestMethod.GET)
	public String requestdetails(@RequestParam("req_id") String req_id, Model model) throws Exception {

		model.addAttribute("request", cmmnrequestService.selectRequest(req_id));
		cmmnrequestService.adminCheck(req_id);
		return "admin/notice/requestDetails";
	}

	//관리자 로그아웃
	@GetMapping("/admin/logout")
	public String adminLogoutPage(Model model) throws Exception {
	
		return "admin/login/adminLogin";
	}
}
