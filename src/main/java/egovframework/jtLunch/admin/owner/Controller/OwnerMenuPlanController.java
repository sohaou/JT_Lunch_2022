package egovframework.jtLunch.admin.owner.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.admin.owner.DTO.MenuPlanDTO;
import egovframework.jtLunch.admin.owner.DTO.SideMenuDTO;
import egovframework.jtLunch.admin.owner.Service.MenuService;
import egovframework.jtLunch.admin.owner.Service.OwnerMenuPlanService;
import net.sf.json.JSONArray;


@Controller
public class OwnerMenuPlanController {
	private static final Logger log = LoggerFactory.getLogger(OwnerMenuPlanController.class);
	@Autowired
	private OwnerMenuPlanService ownerMenuPlanService;
	@Autowired
	private MenuService menuService;

	//식당운영자 식단표 저장
	@RequestMapping(value="/owner/menuplanSave", method=RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String menuplanSave(MenuPlanDTO dto, @RequestParam(value="sideDish[]") List<String> sideDish){
		String resultMsg = "";
		String date = dto.getToday_date();
		SideMenuDTO sideMenuDTO = new SideMenuDTO();
		try {
			ownerMenuPlanService.insertTodayMenu(dto);
			for (int i = 0; i < sideDish.size(); i++) {
				if (sideDish.get(i) != null){
					sideMenuDTO.setMenuplan_date(date);
					sideMenuDTO.setSide_dish(sideDish.get(i));
					ownerMenuPlanService.insertTodayMenuSide(sideMenuDTO);
				}
			}
			resultMsg = "식단등록완료";
		} catch(Exception e){
			e.printStackTrace();
			log.error(e.toString());
			resultMsg = "식단등록실패";
		}
		return resultMsg;
	}
	
	//식당운영자 _ 일별 식단 등록 페이지 이동(캘린더)
	@GetMapping("/owner/dailyDietCalendar")
	public String menuPlanInsertCalendar() throws Exception {
		return "admin/owner/menu/dailyDietCalendar";
	}

	//식당운영자 _ 일별 식단 등록 페이지에서 달력 안의 날짜와 데이터 로드
	@RequestMapping(value = "/owner/menuPlanLoad", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView menuPlanLoad(DateData dateData) throws Exception {	
		ModelAndView mv = new ModelAndView("jsonView");
		Calendar cal = Calendar.getInstance();
		DateData calendarData;
		
		// 검색 날짜
		// 입력한 날짜가 null이면 현재 날짜로 set
		if (dateData.getDate().equals("") && dateData.getMonth().equals("")){
			dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)),String.valueOf(cal.get(Calendar.MONTH)),String.valueOf(cal.get(Calendar.DATE)),null);
		}
		
		// 오늘 식단
		Map<String, Integer> todayMenuPlan =  dateData.today_info(dateData);
		// 날짜(년, 월, 일)을 담기위한 리스트
		List<DateData> dateList = new ArrayList<DateData>();
		// 선택한 날짜에 등록되어 있는 반찬 가져오기
		List<SideMenuDTO> month_sideDish = ownerMenuPlanService.MonthMenuPlanSide(dateData);
		// 선택한 날짜에 등록되어 있는 식단 (밥, 국) 가져오기
		//List<MenuPlanDTO> month_menuPlan = ownerMenuPlanService.selectDayMenuPlan(dateData);
/////////////
		List<MenuPlanDTO>  month_menuPlan= ownerMenuPlanService.dailyDietAll(dateData);
		// 날짜별 식단을 담기위한 리스트
		Map<String, MenuPlanDTO> daily_diet= new HashMap<String, MenuPlanDTO>();
		// 식단이 등록되어 있는 날짜 가져오기
		//List<String> menuPlanDate = ownerMenuPlanService.AteUserInMonth(dateData);
		//Map<String, List<MenuPlanDTO>> ateuser_count= new HashMap<String, String>();
		//
		// 실질적인 달력 데이터 리스트에 데이터 삽입 시작. 
		// 일단 시작 인덱스까지 아무것도 없는 데이터 삽입
		for (int i = 1; i < todayMenuPlan.get("start"); i++){
			calendarData = new DateData(null, null, null, null);
			dateList.add(calendarData);
		}
			
		// 날짜 삽입
		for (int i = todayMenuPlan.get("startDay"); i <= todayMenuPlan.get("endDay"); i++) {
			if (i == todayMenuPlan.get("today")){
				calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()), String.valueOf(i), "today");
			} else{
				calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()), String.valueOf(i), "normal_date");
			}
			dateList.add(calendarData);
		}

		// 달력 빈곳 빈 데이터로 삽입
		int index = 7 - dateList.size() % 7;
		
		if (dateList.size() % 7 != 0){	
			for (int i = 0; i < index; i++){
				calendarData = new DateData(null, null, null, null);
				dateList.add(calendarData);
			}
		}
		
		
		for (int i = 0; i < month_menuPlan.size(); i++) {
			daily_diet.put(Integer.parseInt(month_menuPlan.get(i).getToday_date()) + "", month_menuPlan.get(i));
		}
		
		mv.addObject("MonthMenuPlan", month_menuPlan);
		// 배열에 담음
		mv.addObject("dateList", dateList);
		// 날짜별 반찬
		mv.addObject("sideDish", JSONArray.fromObject(month_sideDish));		
		mv.addObject("today_info", todayMenuPlan);
		mv.addObject("daily_diet",daily_diet);

		return mv;
	}
		
	// 식당운영자 _ 선택한 날짜 식단표 출력
	@RequestMapping(value = "/owner/printTodayMenuPlan", method = RequestMethod.POST)
	public ModelAndView printTodayMenuPlan(@RequestParam("selectDate") String select_date) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		// 선택한 날짜의 식단의 반찬을 담는 리스트
		List<SideMenuDTO> sideDish = ownerMenuPlanService.SelectDateMenuPlanSide(select_date);
		// 선택한 날짜에 식단이 존재하는지 확인
		int check = ownerMenuPlanService.checkMenuIn(select_date);
		if (check == 0){
			// check == 0이면 선택한 날짜에 식단이 존재하지 않는 것.
			mv.addObject("check",check);
		} else{
			// 선택한 날짜의 식단 출력
			MenuPlanDTO menuPlanDTO = ownerMenuPlanService.SelectDateMenuPlan(select_date);
			
			mv.addObject("result", menuPlanDTO);
			mv.addObject("sidedish", JSONArray.fromObject(sideDish));
			mv.addObject("check",check);	
		}			
		return mv;
	}
		
	// 식당운영자 _ 선택한 날짜 식단표 수정 (수정할 정보 가져오기)
	@RequestMapping(value = "/owner/menuInCheck", method = RequestMethod.POST)
	public ModelAndView menuInCheck(@RequestParam("selectDate") String select_date) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		
		// 선택한 날짜에 등록된 식단이 있는지 확인
		int check = ownerMenuPlanService.checkMenuIn(select_date);
		// 선택한 날짜에 등록되어 있는 식단 가져오기(밥, 국)
		MenuPlanDTO dto = ownerMenuPlanService.SelectDateMenuPlan(select_date);
		// 선택한 날짜에 등록되어 있는 반찬 가져오기
		List<SideMenuDTO> sidedish = ownerMenuPlanService.SelectDateMenuPlanSide(select_date);
		// DB 테이블에 저장되어 있는 메뉴 리스트 가져오기
		//List<MenuDTO> category = menuService.printMenuAll();
		
		mv.addObject("category", null);
		mv.addObject("todayMenu", dto);
		mv.addObject("sidedish", JSONArray.fromObject(sidedish));
		mv.addObject("check",check);

		return mv;
	}
	
	// 식당운영자 _ 식단 수정 저장
	@RequestMapping(value = "/owner/menuPlanUpdateSave", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String menuPlanUpdateSave(MenuPlanDTO dto, @RequestParam(value="sideDish[]") List<String> sideDish){			
		String resultMsg = "";
		String date = dto.getToday_date();
		SideMenuDTO sideMenuDTO = new SideMenuDTO();
		
		try {
			ownerMenuPlanService.UpdateMenuPlan(dto);
			ownerMenuPlanService.DeleteMenuPlanSide(date);
			for (int i = 0; i < sideDish.size(); i++){
				if (sideDish.get(i) != null){
					sideMenuDTO.setMenuplan_date(date);
					sideMenuDTO.setSide_dish(sideDish.get(i));
					ownerMenuPlanService.insertTodayMenuSide(sideMenuDTO);
				}
			}
			resultMsg = "식단수정완료";
		} catch(Exception e){
			e.printStackTrace();
			log.error(e.toString());
			resultMsg = "식단수정실패";
		}
		return resultMsg;
	}
}
