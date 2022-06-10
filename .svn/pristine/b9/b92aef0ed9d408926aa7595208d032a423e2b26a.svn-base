package egovframework.jtLunch.admin.owner.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.admin.owner.DTO.MenuDTO;
import egovframework.jtLunch.admin.owner.DTO.MenuPlanDTO;
import egovframework.jtLunch.admin.owner.Service.MenuService;
import egovframework.jtLunch.admin.owner.Service.OwnerMenuPlanService;
import net.sf.json.JSONArray;

@Controller
public class OwnerMenuPlanController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private OwnerMenuPlanService ownerMenuPlanService;

	//식당운영자 식단표 저장
	@RequestMapping(value="/owner/menuplanSave", method=RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String menuplanSave(HttpServletRequest re){
		String resultMsg = "";
		String date = re.getParameter("SelectDate");
		String rice = re.getParameter("rice");
		String soup = re.getParameter("soup");
		String sideDish = re.getParameter("sideDish_insert0");

		for(int i = 1; i < 10; i++) {
			String str = "sideDish_insert"+i;
			if(re.getParameter(str) != null) {
				sideDish = sideDish + "/" + re.getParameter(str) ;
			}
		}

		MenuPlanDTO dto = new MenuPlanDTO();
		
		try {
			dto.setToday_date(date);
			dto.setSteamed_rice(rice);
			dto.setSoup(soup);
			dto.setSide_dish(sideDish);
				
			ownerMenuPlanService.insertTodayMenu(dto);
			resultMsg = "식단등록완료";
		} catch(Exception e){
			resultMsg = "식단등록실패";
		}
		return resultMsg;
	}
		
	//식당운영자 _ 식단표 수정 저장
	@RequestMapping(value = "/owner/menuPlanUpdateSave", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String menuPlanUpdateSave(HttpServletRequest re){			
		String resultMsg = "";
		String today_date = re.getParameter("SelectDate");
		String rice = re.getParameter("rice");
		String soup = re.getParameter("soup");
		String sideDish = re.getParameter("sideDish_modify0");

		for(int i = 1; i < 10; i++) {
			String str = "sideDish_modify" + i;
			if(re.getParameter(str) != null) {
				sideDish = sideDish + "/" + re.getParameter(str) ;
			}
		}
			
		MenuPlanDTO dto = new MenuPlanDTO();
		try {
			dto.setToday_date(today_date);
			dto.setSteamed_rice(rice);
			dto.setSoup(soup);
			dto.setSide_dish(sideDish);
				
			ownerMenuPlanService.UpdateMenuPlan(dto);
			resultMsg = "식단수정완료";
		} catch(Exception e){
			resultMsg = "식단수정실패";
		}
		return resultMsg;
	}
	
	//식당운영자 _ 일별 식단 등록 페이지 이동(캘린더)
	@RequestMapping(value = "/owner/menuPlanInsertCalendar", method = RequestMethod.GET)
	public String menuPlanInsertCalendar(HttpServletRequest re, Model model, DateData dateData) throws Exception {	
		Calendar cal = Calendar.getInstance();
		DateData calendarData;
		List<MenuDTO> category = menuService.printMenuAll();
		
		//검색 날짜
		if(dateData.getDate().equals("") && dateData.getMonth().equals("")){
			dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)),String.valueOf(cal.get(Calendar.MONTH)),String.valueOf(cal.get(Calendar.DATE)),null);
		}
		
		Map<String, Integer> today_info =  dateData.today_info(dateData);
		List<DateData> dateList = new ArrayList<DateData>();

		List<MenuPlanDTO> ateuser_list = ownerMenuPlanService.selectDayMenuPlan(dateData);
		// 달력데이터에 넣기 위한 배열 추가
		MenuPlanDTO[][] selectDayMenulist = new MenuPlanDTO[32][100];
		if (ateuser_list.isEmpty() != true) {
			int j = 0;
			for (int i = 0; i < ateuser_list.size(); i++) {
				int date = Integer.parseInt(String.valueOf(ateuser_list.get(i).getToday_date()).substring(
						   String.valueOf(ateuser_list.get(i).getToday_date()).length() - 2,
						   String.valueOf(ateuser_list.get(i).getToday_date()).length()));
				if (i > 0) {
					int date_before = Integer.parseInt(String.valueOf(ateuser_list.get(i - 1).getToday_date())
									  .substring(String.valueOf(ateuser_list.get(i - 1).getToday_date()).length() - 2,
									  String.valueOf(ateuser_list.get(i - 1).getToday_date()).length()));
					if (date_before == date) {
						j = j + 1;
						selectDayMenulist[date][j] = ateuser_list.get(i);
					} else {
						j = 0;
						selectDayMenulist[date][j] = ateuser_list.get(i);
					}
				} else {
					selectDayMenulist[date][j] = ateuser_list.get(i);
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
			MenuPlanDTO[] ateuser_data = new MenuPlanDTO[100];
			ateuser_data = selectDayMenulist[i];
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
			
		//배열에 담음
		model.addAttribute("dateList", dateList);	
		model.addAttribute("today_info", today_info);
		model.addAttribute("category", JSONArray.fromObject(category));

		return "admin/owner/menu/menuPlanInsertCalendar";
	}
		
	//식당운영자 _ 선택한 날짜 식단표 출력
	@RequestMapping(value = "/owner/printTodayMenuPlan", method = RequestMethod.POST)
	public ModelAndView printTodayMenuPlan(@RequestParam("selectDate") String select_date) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		String[] sidedish = null;
		int check = ownerMenuPlanService.checkMenuIn(select_date);

		if(check == 0) {
			mv.addObject("check",check);
		} else{
			MenuPlanDTO dto = ownerMenuPlanService.SelectDateMenuPlan(select_date); //선택한 날짜의 식단 출력
			String side = dto.getSide_dish();

			for(int i = 0; i < side.length(); i++) {
				sidedish = side.split("/");
			}

			mv.addObject("result", dto);
			mv.addObject("sidedish", sidedish);
			mv.addObject("check",check);	
		}			
		return mv;
	}
		
	//식당운영자 _ 선택한 날짜 식단표 수정 (수정할 정보 가져오기)
	@RequestMapping(value = "/owner/menuInCheck", method = RequestMethod.POST)
	public ModelAndView menuInCheck(@RequestParam("selectDate") String select_date) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");

		int check = ownerMenuPlanService.checkMenuIn(select_date);
		MenuPlanDTO dto = ownerMenuPlanService.SelectDateMenuPlan(select_date);
		List<MenuDTO> category = menuService.printMenuAll();
		
		mv.addObject("category", category);
		mv.addObject("todayMenu", dto);
		mv.addObject("check",check);

		return mv;
	}
}
