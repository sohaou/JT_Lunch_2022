package egovframework.jtLunch.admin.owner.Controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import egovframework.jtLunch.admin.owner.DTO.MenuDTO;
import egovframework.jtLunch.admin.owner.DTO.MenuPlanDTO;
import egovframework.jtLunch.admin.owner.Service.CheckAteUserService;
import egovframework.jtLunch.admin.owner.Service.MenuService;
import egovframework.jtLunch.admin.owner.Service.OwnerMenuPlanService;
import egovframework.jtLunch.main.qrcode.DTO.QrCodeDTO;

@Controller
public class OwnerController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private CheckAteUserService checkateuserService;
	@Autowired
	private OwnerMenuPlanService ownerMenuPlanService;
	
	//식당운영자 main
	@GetMapping("/owner/main")
	public String OwnerMain(Model model) throws Exception {
		MenuPlanDTO dto = ownerMenuPlanService.todayMenuPlan();
		int reserveCount = ownerMenuPlanService.countReserve();
		int ate_user = ownerMenuPlanService.countAte_user();
		//모든 메뉴의 좋아요 수 합
		int total_like_count = 0;
		//좋아요 수 상위 10개 메뉴의 좋아요 수 합
		int top10_count = 0; 
		//상위 10개 메뉴를 저장하는 리스트
		List<MenuDTO> menuTop = null;
		//모든 메뉴를 저장하는 리스트
		List<MenuDTO> menuall = null; 
		menuTop = menuService.selectLikeCount10();
		menuall = menuService.printMenuAll();
		
		for(int i = 0; i < menuall.size(); i++) {
			//모든 메뉴의 좋아요 수 합
			total_like_count += menuall.get(i).getLike_count();
		}
		for(int i = 0; i < menuTop.size(); i++) {
			//상위 10개 메뉴의 좋아요 수 합
			top10_count += menuTop.get(i).getLike_count(); 
		}
		model.addAttribute("total_like_count", total_like_count);
		//기타 ( 전체 메뉴 좋아요 수 - 상위 10개 메뉴 좋아요 수)
		model.addAttribute("etc", total_like_count-top10_count); 
		model.addAttribute("menuTop", menuTop); 
		model.addAttribute("reserveCount", reserveCount);
		model.addAttribute("ate_user", ate_user);
		model.addAttribute("todayMenu", dto);
		
	
		return "admin/owner/menu/todayMenuPlanner";
	}
	
	//식당운영자 _ 월/일별 식수 인원 확인 (캘린더)
	@RequestMapping(value = "/owner/ownerCalendar", method = RequestMethod.GET)
	public String calendar(Model model,DateData dateData) throws Exception{
		DecimalFormat decFormat = new DecimalFormat("###,###");
		Calendar cal = Calendar.getInstance();
		DateData calendarData;
		//검색 날짜
		if(dateData.getDate().equals("") && dateData.getMonth().equals("")){
			dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)),String.valueOf(cal.get(Calendar.MONTH)),String.valueOf(cal.get(Calendar.DATE)),null);
		}
		//검색 날짜 end

		Map<String, Integer> today_info =  dateData.today_info(dateData);
		List<DateData> dateList = new ArrayList<DateData>();
		// 검색 날짜 end
		
		List<QrCodeDTO> ateuser_list = checkateuserService.ateuser_list(dateData);
		
		// 달력데이터에 넣기 위한 배열 추가
		QrCodeDTO[][] ateuser = new QrCodeDTO[32][100];
		if (ateuser_list.isEmpty() != true) {
			int j = 0;
			for (int i = 0; i < ateuser_list.size(); i++) {
			int date = Integer.parseInt(String.valueOf(ateuser_list.get(i).getAte_date()).substring(
				String.valueOf(ateuser_list.get(i).getAte_date()).length() - 2,
				String.valueOf(ateuser_list.get(i).getAte_date()).length()));
				if (i > 0) {
					int date_before = Integer.parseInt(String.valueOf(ateuser_list.get(i - 1).getAte_date())
							.substring(String.valueOf(ateuser_list.get(i - 1).getAte_date()).length() - 2,
										String.valueOf(ateuser_list.get(i - 1).getAte_date()).length()));
					if (date_before == date) {
						j = j + 1;
						ateuser[date][j] = ateuser_list.get(i);
					} else {
						j = 0;
						ateuser[date][j] = ateuser_list.get(i);
					}
				} else {
					ateuser[date][j] = ateuser_list.get(i);
				}
			}
		}
		//실질적인 달력 데이터 리스트에 데이터 삽입 시작.
		//일단 시작 인덱스까지 아무것도 없는 데이터 삽입
		for(int i = 1; i < today_info.get("start"); i++){
			calendarData = new DateData(null, null, null, null);
			dateList.add(calendarData);
		}
		
		//날짜 삽입
		for (int i = today_info.get("startDay"); i <= today_info.get("endDay"); i++) {
			QrCodeDTO[] ateuser_data = new QrCodeDTO[100];
			ateuser_data = ateuser[i];
			if(i == today_info.get("today")){
				calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()), String.valueOf(i), "today", ateuser_data);
			}else{
				calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()), String.valueOf(i), "normal_date", ateuser_data);
			}
			dateList.add(calendarData);
		}

		//달력 빈곳 빈 데이터로 삽입
		int index = 7-dateList.size() % 7;
		
		if(dateList.size() % 7 != 0){
			
			for (int i = 0; i < index; i++) {
				calendarData = new DateData(null, null, null, null);
				dateList.add(calendarData);
			}
		}

		int count = checkateuserService.countAteUser(dateData);
		String money = decFormat.format(count*7000);
		//배열에 담음
		//날짜 데이터 배열
		model.addAttribute("dateList", dateList);		
		model.addAttribute("today_info", today_info);
		//이번 달 전체 식사 횟수
		model.addAttribute("countate", count);
		//이번 달 금액 (전체 식사 횟수 x 단가)
		model.addAttribute("money", money); 
		
		return "admin/owner/printAteUser/ownerCalendar";
	}

	//식당운영자 _ 월/일별 식수 인원 리스트 확인 (캘린더->리스트)
	@RequestMapping(value = "/owner/printAteUser", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView ateUserShow(@RequestParam("ate_date") String ate_date, Model model) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		List<QrCodeDTO> dto = checkateuserService.DayAteUserAll(ate_date);	
		QrCodeDTO[] ateuserlist = new QrCodeDTO[100];
		String date = "";
		String atedate = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		for(int i = 0; i < dto.size(); i++) {
			ateuserlist[i] = dto.get(i);
			date = format.format(dto.get(i).getAte_date());
			String[] atedatesplit = date.split("-");
			atedate = atedatesplit[0].substring(2,4) + "/" + atedatesplit[1] + "/" + atedatesplit[2];
		}

		mv.addObject("result", ateuserlist);
		mv.addObject("atedate", atedate);

		return mv;
	}
	
	//식당운영자 _ 월별 총 계산 페이지로 이동
	@GetMapping("/owner/totalCount")
	public String totalCount(){	
		return "admin/owner/printAteUser/totalCount";
	}	
}