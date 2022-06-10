package egovframework.jtLunch.admin.cmmn.Controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.jtLunch.admin.cmmn.Service.AdminCmmnService;
import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.admin.owner.DTO.MenuPlanDTO;
import egovframework.jtLunch.admin.owner.DTO.SearchResultDTO;
import egovframework.jtLunch.admin.owner.DTO.SideMenuDTO;
import egovframework.jtLunch.admin.owner.Service.MenuService;
import egovframework.jtLunch.admin.owner.Service.OwnerMenuPlanService;
import net.sf.json.JSONArray;

@Controller
//기간별 정산
public class AdminCmmnController {
	@Autowired
	private AdminCmmnService adminCmmnService;
	@Autowired
	private OwnerMenuPlanService ownerMenuPlanService;
	@Autowired
	private MenuService menuService;
	private static final Logger log = LoggerFactory.getLogger(AdminCmmnController.class);
	
	@RequestMapping(value = "/cmmn/totalCalculate", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView totalCalculate(HttpServletRequest re, Model model) throws Exception {	
		
		DecimalFormat decFormat = new DecimalFormat("###,###");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		ModelAndView mv = new ModelAndView("jsonView");
		String startDate=re.getParameter("startDate");
		String endDate=re.getParameter("endDate");

		DateData dateData=new DateData();
		dateData.setStartDate(startDate);
		dateData.setEndDate(endDate);
		int ateUser=0;
		
		Date sd = format.parse(startDate);
		Date ed = format.parse(endDate);
		ArrayList<String> dates = new ArrayList<String>();
		SearchResultDTO[] ateuserlist=new SearchResultDTO[100];
		List<SearchResultDTO> dto= new ArrayList<SearchResultDTO>();
		
		try {
			Date currentDate = sd;
			while (currentDate.compareTo(ed) <= 0) {
				dates.add(format.format(currentDate));
				Calendar c = Calendar.getInstance();
				c.setTime(currentDate);
				c.add(Calendar.DAY_OF_MONTH, 1);
				currentDate = c.getTime();
			}
			for(int i=0;i<dates.size();i++) {
				SearchResultDTO srdto=new SearchResultDTO();
				int ateResult=0;
				ateResult=adminCmmnService.CountAteTermTable(dates.get(i));
				srdto.setEatDate(dates.get(i));
				srdto.setAteUserCount(ateResult);
				String tmoney=decFormat.format(ateResult*7000);
				srdto.setTotalMoney(tmoney);
				dto.add(i,srdto);
			}
			for(int i=0;i<dto.size();i++) {
				ateuserlist[i]=dto.get(i);
			}
			ateUser=adminCmmnService.CountAteTerm(dateData);
			String money=decFormat.format(ateUser*7000);
			
			mv.addObject("ateUser",ateUser);
			mv.addObject("money",money);
			mv.addObject("msg","검색완료!");
			mv.addObject("result",ateuserlist);
		} catch(Exception e) {
			e.printStackTrace();
			log.error(e.toString());
		}
		
		
		return mv;
	}
	//식당운영자 _ 일별 식단 등록 페이지 이동(캘린더)
	@GetMapping("/admin/dailyDietCalendar")
	public String menuPlanInsertCalendar() throws Exception {
		return "admin/menu/dailyDietCalendar";
	}

	//식당운영자 _ 일별 식단 등록 페이지에서 달력 안의 날짜와 데이터 로드
	@RequestMapping(value = "/admin/menuPlanLoad", method = RequestMethod.POST)
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
}
