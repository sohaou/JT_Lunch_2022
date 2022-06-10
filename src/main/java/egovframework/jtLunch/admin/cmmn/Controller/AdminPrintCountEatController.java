package egovframework.jtLunch.admin.cmmn.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.jtLunch.admin.cmmn.UseDateData;
import egovframework.jtLunch.admin.cmmn.DTO.DailyEatCountDTO;
import egovframework.jtLunch.admin.cmmn.DTO.FranchiseDTO;
import egovframework.jtLunch.admin.cmmn.Service.AdminDailyEatCountService;
import egovframework.jtLunch.admin.cmmn.Service.AdminFranchiseService;
import net.sf.json.JSONArray;

@Controller
public class AdminPrintCountEatController {
	
	private static Logger log = LoggerFactory.getLogger(AdminPrintCountEatController.class);	
	
	@Autowired
	private AdminDailyEatCountService service1;
	@Autowired
	private AdminFranchiseService franchiseService;
	// 체인점별 전날 식사자 수 출력 페이지로 이동
	@RequestMapping(value = "/admin/dailyEatCount", method = RequestMethod.GET)
	public String printDailyEatCount(Model model) throws Exception {
		// 전날 날짜 
		String eatDate = new UseDateData().getYesterday();
		// 전날 체인점별 식사자 수
		List<DailyEatCountDTO> result = service1.printDailyEatCount(eatDate);
		// 전날 체인점별 식사자 수 총합
		int total = 0;
		// JSON 배열 형태로 만들기
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		
		for(DailyEatCountDTO vo : result) {
			total += vo.getCOUNT_EAT();
			jsonObj.put("countEat", vo.getCOUNT_EAT());
			jsonObj.put("shopName", vo.getSHOP_NAME());
			jsonArr.add(jsonObj);
			// jsonObj.put("franchiseID", vo.getFRANCHISE_ID());
			//jsonObj.put("eatDate", vo.getEAT_DATE());
		}
		// 식사날짜
		model.addAttribute("eatDate", eatDate);
		// 지점명, 식사자 수 정보
		model.addAttribute("chartJSON", jsonArr);
		// 식사자 수 총합
		model.addAttribute("total", total);
		
		return "admin/dailyEatCount/printDailyEatCount";
	}
	
	// 식사자 수 검색 페이지로 이동
	@RequestMapping(value="/admin/searchEatCount", method = RequestMethod.GET)
	public String searchEatCount() throws Exception{
		return "admin/dailyEatCount/searchDailyEatCount";
	}
	
	// 셀렉트박스 set
	@RequestMapping(value="/getSelect", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public ModelAndView getSelect() throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		
		// DB에 저장되어 있는 모든 체인점 정보
		List<FranchiseDTO> franchise = franchiseService.printAllFranchise();
		// 처음 페이지에서는 지난 
		// DB에서 가져온 체인점 정보를 json형태로 바꾸기 위해
		JSONArray franchiseArr = new JSONArray();
		JSONObject franchiseObj = new JSONObject();
		
		for(FranchiseDTO vo : franchise) {
			franchiseObj.put("id", vo.getID());
			franchiseObj.put("shopName", vo.getSHOPNAME());
			franchiseArr.add(franchiseObj);
		}
		
		mv.addObject("result", franchise);
		
		return mv;
	}
	
	// 식사자 수 검색
	@RequestMapping(value="/search/dailyEatCount", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public ModelAndView searchResult(HttpServletRequest re) throws ParseException{
		ModelAndView mv = new ModelAndView("jsonView");
		
		String id = re.getParameter("id");
		String startDate = re.getParameter("startDate");
		String endDate = re.getParameter("endDate");
		
		List<DailyEatCountDTO> searchResult = new ArrayList<>();
		List<DailyEatCountDTO> totalResult = new ArrayList<>();
		
		if(id == null || id == "" && startDate == null || startDate == "" && endDate == null || endDate == "") {
			// 오늘날짜와 한달 전 날짜 가져오기
			UseDateData dateData = new UseDateData();
			startDate = dateData.getCalcDate("m", -1);
			endDate = dateData.getToday();
		} 
		try {
			searchResult = service1.searchDailyEatCount(id, startDate, endDate);
			totalResult = service1.searchDailyEatCount(null, startDate, endDate);
		} catch(Exception e) {
			e.printStackTrace();
			log.error(e.toString());
		}
		
		for(int i = 0; i < totalResult.size(); i++) {
			for(int j = 0; j < searchResult.size(); j++) {
				if(totalResult.get(i).getEAT_DATE().equals(searchResult.get(j).getEAT_DATE())) {
					searchResult.get(j).setTOTAL(totalResult.get(i).getCOUNT_EAT());
				}
			}
		}
		// 최근 한달간의 총 식사자 수를 가져옴
		
		JSONArray searchResultArr = new JSONArray();
		JSONObject searchResultObj = new JSONObject();
		
		for(DailyEatCountDTO vo : searchResult) {
			searchResultObj.put("date", new UseDateData().convertDate(vo.getEAT_DATE()));
			searchResultObj.put("value", vo.getCOUNT_EAT());
			searchResultObj.put("total", vo.getTOTAL());
			searchResultArr.add(searchResultObj);
		}
		
		mv.addObject("result", searchResultArr);
		
		return mv;
	}
}
