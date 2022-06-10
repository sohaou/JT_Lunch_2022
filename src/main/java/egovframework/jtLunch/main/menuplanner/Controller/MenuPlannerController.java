package egovframework.jtLunch.main.menuplanner.Controller;

import javax.inject.Inject;

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
import egovframework.jtLunch.main.menuplanner.DTO.MenuPlannerDTO;
import egovframework.jtLunch.main.menuplanner.Service.MenuPlannerService;
import egovframework.jtLunch.main.reservation.DTO.ReservationDTO;
import egovframework.jtLunch.main.reservation.DTO.ReservationQRDTO;
import egovframework.jtLunch.main.reservation.Service.ReservationService;

/**

  * @Method Name : MainPageController

  * @작성일 : 2021. 10. 12

  * @Method 설명 : 메인쪽 페이지 불러오는 곳

  */

@Controller
public class MenuPlannerController {
	
	Logger log = Logger.getLogger(getClass());
	
	@Inject
	UserService userservice;
	
	@Inject
	MenuPlannerService menuService;
	
	@Inject
	ReservationService reservationService;
	
	@RequestMapping(value="/user/menuplanner", method=RequestMethod.GET)
	public String menuplanner(Model model,Authentication authentication, ReservationDTO reservationDTO, ReservationQRDTO reservationQRDTO) throws Exception{
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		
		String id = "";
		if(principal != null) {
			id = auth.getName();	
		}
		
		CustomUserDetails dto = userservice.selectUser(id);
		
		
		String res_id = dto.getUser_id();
		String res_name = dto.getUser_name();
		
		String qr_id = dto.getUser_id();
		
		reservationDTO.setRes_id(res_id);
		reservationDTO.setRes_name(res_name);
		
		reservationQRDTO.setId(qr_id);
		log.debug("=======================" + reservationDTO + "======================");

		try {
			System.out.println("------------사용자 정보 가져옴-----------------");
			ReservationDTO rdto = reservationService.selectUser(id);
			ReservationQRDTO qrdto = reservationService.selectQrUser(id);
			
			model.addAttribute("qrdto", qrdto);
			model.addAttribute("rdto",rdto);
			log.debug(rdto+"======================================================");
			log.debug(qrdto+"======================================================");
			
		} catch(Exception e) {
			System.out.println("------------정보를 가져올 수 없음!!!!!!!!!");
			System.out.println(e.getMessage());
		}
		return "/main/menuplanner/menuPlanner";
	}
	
//	// 밥 좋아요 카운트 증가
//	@RequestMapping(value="/user/likeCountSteamedRice", method=RequestMethod.GET)
//	public String likeCountRice(@RequestParam("menu_name")String steamed_rice) throws Exception{
//
//		menuService.likecountrice(steamed_rice);
//		return "redirect:/user/menuplanner";
//	}
//	
//	// 국 좋아요 카운트 증가
//	@RequestMapping(value="/user/likeCountSoup", method=RequestMethod.GET)
//	public String likeCountSoup(@RequestParam("menu_name")String soup) throws Exception{
//		
//		menuService.likecountsoup(soup);
//		return "redirect:/user/menuplanner";
//	}
//	
//	//반찬 좋아요 카운트 증가
//	@RequestMapping(value="/user/likeCountSideDish", method=RequestMethod.GET)
//	public String likeCountSideDish(@RequestParam("menu_name")String side_dish) throws Exception{
//		
//		menuService.likecountsidedish(side_dish);
//		return "redirect:/user/menuplanner";
//	}
//	
	@RequestMapping(value="/user/print", method=RequestMethod.POST)
	public ModelAndView print(Model model) throws Exception{
		
		ModelAndView mv = new ModelAndView("jsonView");
		MenuPlannerDTO dto = menuService.selectmenu();
		
		mv.addObject("menuplanner", dto);
		log.debug(dto);
		
		return mv;
	}
				
}