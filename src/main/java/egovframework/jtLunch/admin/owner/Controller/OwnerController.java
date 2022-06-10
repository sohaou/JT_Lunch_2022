package egovframework.jtLunch.admin.owner.Controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.admin.owner.DTO.MenuPlanDTO;
import egovframework.jtLunch.admin.owner.DTO.SideMenuDTO;
import egovframework.jtLunch.admin.owner.Service.CheckAteUserService;
import egovframework.jtLunch.admin.owner.Service.OwnerMenuPlanService;
import egovframework.jtLunch.main.qrcode.DTO.QrCodeDTO;
import net.sf.json.JSONArray;

@Controller
public class OwnerController {
	@Autowired
	private CheckAteUserService checkateuserService;
	@Autowired
	private OwnerMenuPlanService ownerMenuPlanService;
	
	//식당운영자 main
	@GetMapping("/owner/main")
	public String ownerMain(Model model) throws Exception {
		// 오늘 날짜에 저장된 식단
		MenuPlanDTO menuPlanDTO = ownerMenuPlanService.todayMenuPlan();
		// 오늘 식사 예약자수
		int reserveCount = ownerMenuPlanService.countReserve();
		// 오늘 식사를 마친 사람의 수
		int ateUserCount = ownerMenuPlanService.countAte_user();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        // 오늘 날짜
        String strToday = sdf.format(c1.getTime());
		// 오늘 날짜 식단의 반찬
		List<SideMenuDTO> sidedish = ownerMenuPlanService.SelectDateMenuPlanSide(strToday);

		model.addAttribute("reserveCount", reserveCount);
		model.addAttribute("ate_user", ateUserCount);
		model.addAttribute("todayMenu", menuPlanDTO);
		model.addAttribute("sidedish", JSONArray.fromObject(sidedish));
	
		return "admin/owner/menu/todayMenuPlanner";
	}

	// 식당운영자 _ 월/일별 식수 인원 확인 페이지로 이동(캘린더)
	@GetMapping("/owner/ownerCalendar")
	public String calendar() throws Exception {
		return "admin/owner/printAteUser/ateUserListCalendar";
	}
	
	// 월/일별 식사 인원 확인 (캘린더)
	@RequestMapping(value = "/owner/AteUserMonth", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView ateUserMonth(DateData dateData) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		Calendar cal = Calendar.getInstance();
		DateData calendarData;
		
		// 검색 날짜가 null 이면 현재 날짜로 set
		if (dateData.getDate().equals("") && dateData.getMonth().equals("")){
			dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)),String.valueOf(cal.get(
		  Calendar.MONTH)),String.valueOf(cal.get(Calendar.DATE)),null); } 
		
		// 검색한 날짜 정보
		Map<String, Integer> searchDate =  dateData.today_info(dateData);
		List<DateData> dateList = new ArrayList<DateData>();
		// 검색한 날짜에 식사를 한 모든 사람의 정보를 저장하는 리스트
		List<Map<String,String>> ateuser_list = checkateuserService.AteUserInMonth(dateData);
		Map<String, String> ateuser_count= new HashMap<String, String>();
		// 실질적인 달력 데이터 리스트에 데이터 삽입 시작.
		// 일단 시작 인덱스까지 아무것도 없는 데이터 삽입
		for (int i = 1; i < searchDate.get("start"); i++){
			calendarData = new DateData(null, null, null, null);
			dateList.add(calendarData);
		}
		
		// 날짜 삽입
		for (int i = searchDate.get("startDay"); i <= searchDate.get("endDay"); i++) {
			if (i == searchDate.get("today")){
				calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()), String.valueOf(i), "today");
			} else{
				calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()), String.valueOf(i), "normal_date");
			}
			dateList.add(calendarData);
		}

		// 달력 빈곳 빈 데이터로 삽입
		int index = 7- dateList.size() % 7;
		
		if (dateList.size() % 7 != 0){
			for (int i = 0; i < index; i++){
				calendarData = new DateData(null, null, null, null);
				dateList.add(calendarData);
			}
		}
		
		for (int i = 0; i < ateuser_list.size(); i++) {
			Map<String, String> ateData = ateuser_list.get(i);
			ateuser_count.put(Integer.parseInt(ateData.get("ate_date")) + "", String.valueOf(ateData.get("ate_count")) + "명");
			
		}
		
		// 이번 달 전체 식사 횟수
		int count = checkateuserService.countAteUser(dateData);
		DecimalFormat decFormat = new DecimalFormat("###,###");
		// 이번 달 정산금액 = 전체 식사 횟수 * 단가(7000원)
		String money = decFormat.format(count * 7000);
		
		mv.addObject("ateuser_count", ateuser_count);
		mv.addObject("dateList", dateList);		
		mv.addObject("today_info", searchDate);
		mv.addObject("countate", count);
		mv.addObject("money", money); 
		
		return mv;
	}

	//식당운영자 _ 월/일별 식수 인원 리스트 확인 (캘린더->리스트)
	@RequestMapping(value = "/owner/AteUserList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView ateUserShow(@RequestParam("ate_date") String ate_date) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		String[] ateDate = ate_date.split("-");
		
		if (Integer.parseInt(ateDate[1]) < 10) {
			ateDate[1] = "0" + ateDate[1];
		}
		if (Integer.parseInt(ateDate[2]) < 10) {
			ateDate[2] = "0" + ateDate[2];
		}
		
		ate_date = ateDate[0] + "-" + ateDate[1] + "-" + ateDate[2];

		List<QrCodeDTO> dto = checkateuserService.DayAteUserAll(ate_date);	

		mv.addObject("result", dto);

		return mv;
	}
	
	//식당운영자 _ 월별 총 계산 페이지로 이동
	@GetMapping("/owner/totalCount")
	public String totalCount(){	
		return "admin/owner/printAteUser/calculateTotalAteCount";
	}	
}