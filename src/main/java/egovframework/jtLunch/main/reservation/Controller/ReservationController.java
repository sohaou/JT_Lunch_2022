package egovframework.jtLunch.main.reservation.Controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;
import egovframework.jtLunch.cmmn.security.Service.UserService;
import egovframework.jtLunch.main.reservation.DTO.DivisionDTO;
import egovframework.jtLunch.main.reservation.DTO.ReservationDTO;
import egovframework.jtLunch.main.reservation.DTO.ReserveTableDTO;
import egovframework.jtLunch.main.reservation.Service.ReservationService;
import egovframework.jtLunch.main.reservation.Service.ReserveTableService;
import net.sf.json.JSONArray;

@Controller
public class ReservationController {
	
	Logger log = Logger.getLogger(getClass());
	
	@Inject
	UserService userservice;
	
	@Inject
	ReservationService reservationService;

	@Inject
	ReserveTableService reserveTableService;
	
	@RequestMapping(value="/user/reservePage", method=RequestMethod.GET)
	public String selectTeam(Model model) throws Exception{
		
		List<DivisionDTO> department = reservationService.selectDepartment();
		List<DivisionDTO> team = reservationService.selectTeam();
		ReserveTableDTO firstTime = reserveTableService.firstTime();
		ReserveTableDTO secondTime = reserveTableService.secondTime();
		ReserveTableDTO thirdTime = reserveTableService.thirdTime();
		ReserveTableDTO fourthTime = reserveTableService.fourthTime();
		
		model.addAttribute("Department", JSONArray.fromObject(department));
		model.addAttribute("Team", JSONArray.fromObject(team));
		model.addAttribute("firstTime", firstTime.getCount());
		model.addAttribute("secondTime", secondTime.getCount());
		model.addAttribute("thirdTime", thirdTime.getCount());
		model.addAttribute("fourthTime", fourthTime.getCount());
		
		log.debug(fourthTime.getCount()+"asdasd");
		return "main/reservation/reservation";
	}
	
	@RequestMapping(value="/user/selectMember", method=RequestMethod.POST)
	public ModelAndView selectMember(Authentication authentication, HttpServletRequest request) throws Exception{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		
		String loginid = "";
		if(principal != null) {
			loginid = auth.getName();	
		}
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		String department = request.getParameter("department").toString();
		String team = request.getParameter("team").toString();
	
		Map<String, Object> requestMap = new HashMap<String, Object>();	
		requestMap.put("department", department);
		requestMap.put("team", team);
		requestMap.put("loginid", loginid);
		
		mv.addObject("selectUser", reservationService.reserveSelectUser(requestMap));
		
		return mv;
	}
	
	@RequestMapping(value="/user/selectTableTime", method=RequestMethod.POST)
	public ModelAndView selectTable(HttpServletRequest request, Model model) throws Exception{
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		String no = request.getParameter("no");
		
		log.debug("----------------------"+no);
		Map<String, Object> requestMap = new HashMap<String, Object>();		
		requestMap.put("time", no);
		
		log.debug("=============================="+requestMap);
		
		mv.addObject("tableTime", reservationService.selectTable(requestMap));
		System.out.println(mv);
		
		return mv;
	}

	@RequestMapping(value="/user/addMember", method=RequestMethod.POST)
	public ModelAndView addMember(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView("jsonView");
		String[] checkArr = request.getParameterValues("check[]");
		System.out.println(checkArr+"asdasdasdsa");
		List<CustomUserDetails> checkUser = new ArrayList<>();
		
			for(int i=0; i<checkArr.length; i++) {
				String id = checkArr[i];
				CustomUserDetails dto = reservationService.selectMember(id);
				checkUser.add(dto);
			}
			mv.addObject("checkUser",checkUser);
		
		System.out.println("askdjlaskdlksajdkl"+checkUser);
		
		return mv;
	}
	
	/*
	@RequestMapping(value="/user/memberAllPrint", method=RequestMethod.POST)
	public ModelAndView memberAllPrint(Model model) throws Exception{
		
		ModelAndView mv = new ModelAndView("jsonView");
		List<CustomUserDetails> memberAllPrint = reservationService.memberAllPrint();
		
		mv.addObject("memberAllPrint", memberAllPrint);
		System.out.println("asdasdsadsadasdasdsadassd"+memberAllPrint);
		return mv;
	}
	*/
	/*
	@RequestMapping(value="/user/reserve", method=RequestMethod.GET)
	public void reserve(Authentication authentication, ReservationDTO reservationDTO, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		
		String id = "";
		if(principal != null) {
			id = auth.getName();	
		}
		
		CustomUserDetails dto = userservice.selectUser(id);
		
		String res_id = dto.getUser_id();
		String res_name = dto.getUser_name();
		String res_tel = dto.getUser_tel();
		String department = dto.getDepartment();
		String team = dto.getTeam();
		reservationDTO.setRes_id(res_id);
		reservationDTO.setRes_name(res_name);
		reservationDTO.setRes_tel(res_tel);
		reservationDTO.setDepartment(department);
		reservationDTO.setTeam(team);
		System.out.println(reservationDTO);
		LocalTime now = LocalTime.now();
		int hour = now.getHour();
		int minute = now.getMinute();

        log.debug("=======================" + hour + minute + "======================"); 
 		log.debug("=======================" + reservationDTO + "======================");
	
 		//10시 30분 이전까지 식사 예약	
 		
		if(hour >= 10 && minute > 30 || hour > 10 && minute < 30) {
			out.println("<script>alert('금일 예약이 마감되었습니다.');location.href='/user/menuplanner';</script>");
			out.flush();
		}
	
		else {
	
			reservationService.reserve(reservationDTO);
			out.println("<script>alert('예약이 완료되었습니다.'); location.href='/user/menuplanner';</script>");
			out.flush();
			
		}
	}*/

	@RequestMapping(value="/user/reserve", method=RequestMethod.GET)
	public ModelAndView reserve(Authentication authentication, HttpServletRequest request, Model model, ReservationDTO reservationDTO, ReserveTableDTO reserveTableDTO) throws Exception{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		
		String loginid = "";
		if(principal != null) {
			loginid = auth.getName();	
		}
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		String[] idArray = request.getParameterValues("user_id[]");
		String no = request.getParameter("no"); 
		
		Map<String, Object> requestMap = new HashMap<String, Object>();	
		requestMap.put("time", no);
		
		Map<String, Object> table = reservationService.selectTable(requestMap);
		Object count = table.get("count");
		
		Map<String, Object> tableMap = new HashMap<String, Object>();
		tableMap.put("no", no);
		tableMap.put("count", count);
		
		reservationService.updateTableCount(tableMap);
		System.out.println("---------------"+table);

		try {
			for(int i=0; i<idArray.length; i++) {
				String id= idArray[i];
				CustomUserDetails dto = reservationService.selectMember(id);
				String res_id = dto.getUser_id();
				String res_name = dto.getUser_name();
				String department = dto.getDepartment();
				String team = dto.getTeam();
				String res_tel = dto.getUser_tel();
				
				reservationDTO.setRes_id(res_id);
				reservationDTO.setRes_name(res_name);
				reservationDTO.setDepartment(department);
				reservationDTO.setTeam(team);
				reservationDTO.setRes_tel(res_tel);
				
				reservationService.reserve(reservationDTO);
			}
				CustomUserDetails loginUser = reservationService.selectMember(loginid);
				String res_id2 = loginUser.getUser_id();
				String res_name2 = loginUser.getUser_name();
				String department2 = loginUser.getDepartment();
				String team2 = loginUser.getTeam();
				String res_tel2 = loginUser.getUser_tel();
				
				reservationDTO.setRes_id(res_id2);
				reservationDTO.setRes_name(res_name2);
				reservationDTO.setDepartment(department2);
				reservationDTO.setTeam(team2);
				reservationDTO.setRes_tel(res_tel2);
				reservationService.reserve(reservationDTO);
				
				mv.addObject("rst_msg", "000");
		}catch(Exception e) {
			mv.addObject("rst_msg", "001");
			e.printStackTrace();
		}
		
		return mv;
	}
}	