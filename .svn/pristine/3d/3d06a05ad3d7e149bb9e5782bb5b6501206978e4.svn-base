package egovframework.jtLunch.admin.cmmn.Controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.jtLunch.admin.cmmn.Service.AdminCmmnService;
import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.admin.owner.DTO.SearchResultDTO;

@Controller
//기간별 정산
public class AdminCmmnController {
	@Autowired
	private AdminCmmnService adminCmmnService;
	
	@RequestMapping(value = "/cmmn/totalCalculate", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView totalCalculate(HttpServletRequest re, Model model) throws Exception {	
		
		DecimalFormat decFormat = new DecimalFormat("###,###");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		ModelAndView mv = new ModelAndView("jsonView");
		String startDate=re.getParameter("startDt");
		String endDate=re.getParameter("endDt");

		DateData dateData=new DateData();
		dateData.setStartDate(startDate);
		dateData.setEndDate(endDate);
		int ateUser=0;
		
		Date sd = format.parse(startDate);
		Date ed = format.parse(endDate);
		ArrayList<String> dates = new ArrayList<String>();
		SearchResultDTO[] ateuserlist=new SearchResultDTO[100];
		List<SearchResultDTO> dto= new ArrayList<SearchResultDTO>();
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
		
		return mv;
	}
}
