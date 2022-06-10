package egovframework.jtLunch.main;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;
import egovframework.jtLunch.cmmn.security.Service.UserService;
import egovframework.jtLunch.main.reservation.DTO.DivisionDTO;
import egovframework.jtLunch.main.reservation.Service.ReservationService;
import net.sf.json.JSONArray;

@Controller
public class MyInfoController {
	
	@Inject
	UserService userservice;
	@Inject
	ReservationService reservationService;
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
	
	@RequestMapping(value="/user/printMyInfo", method=RequestMethod.GET)
	public String printMyInfo(Model model,Authentication authentication) throws Exception{
		List<DivisionDTO> department = reservationService.selectDepartment();
		List<DivisionDTO> team = reservationService.selectTeam();
		String[][] valueArr = new String[50][100];
	
		for(int j = 0; j < department.size(); j++) {
			for(int i = 0; i < team.size(); i++) {
				if(department.get(j).getDepartment().equals(team.get(i).getDepartment())) {
					valueArr[j][0] = department.get(j).getDepartment();
					valueArr[j][i + 1] = team.get(i).getTeam();
				}
			}	
		}
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		
		String id = "";
		if(principal != null) {
			id = auth.getName();	
		}
	
		CustomUserDetails dto = userservice.selectUser(id);
		model.addAttribute("Department", JSONArray.fromObject(department));
		model.addAttribute("Team", JSONArray.fromObject(team));
		model.addAttribute("myinfo",dto);
		model.addAttribute("valueArr",JSONArray.fromObject(valueArr));

		return "/user/myinfo/MyInfoMain";
	}
	
	//사용자 요청사항 등록 저장
	@RequestMapping(value = "/user/myInfoUpdate", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String myInfoUpdate(CustomUserDetails customUserdetails){
		String msg = "";
		String PASSWORD = pwdEncoder.encode(customUserdetails.getPassword());
		customUserdetails.setPassword(PASSWORD);
		try {
			userservice.UpdateMyInfo(customUserdetails);
			msg = "수정 완료";
		} catch (Exception e) {
			msg = "수정 실패";
		}
		return msg;
	}
}