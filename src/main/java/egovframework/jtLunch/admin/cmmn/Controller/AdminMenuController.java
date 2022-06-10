package egovframework.jtLunch.admin.cmmn.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.jtLunch.admin.cmmn.UseDateData;
import egovframework.jtLunch.admin.owner.DTO.MenuDTO;
import egovframework.jtLunch.admin.owner.DTO.SideMenuDTO;
import egovframework.jtLunch.admin.owner.Service.MenuService;
import egovframework.jtLunch.admin.owner.Service.OwnerMenuPlanService;
import net.sf.json.JSONArray;

@Controller

public class AdminMenuController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private OwnerMenuPlanService ownerMenuPlanService;
	private static final Logger log = LoggerFactory.getLogger(AdminMenuController.class);
	
	// 날짜 정보 가져오기 위한 클래스
	UseDateData udd = new UseDateData();
	
	// 식당운영자 _ 저장되어 있는 모든 메뉴 정보 가져오기
	@PostMapping("/admin/menuPlanner")
	public ModelAndView menuPlanner() throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		
        String strToday = udd.getToday();
		List<MenuDTO> category = menuService.printMenuAll();
		List<SideMenuDTO> sidedish = ownerMenuPlanService.SelectDateMenuPlanSide(strToday);
		
		mv.addObject("category", category);
		mv.addObject("sidedish", JSONArray.fromObject(sidedish));	
		
		return mv;
	}
	
	// 식당운영자 메뉴 등록 내용 저장
	@RequestMapping(value="/admin/menuSave", method=RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String MenuSave(MenuDTO vo) throws Exception{
		String resultMsg = "";

		//입력한 메뉴명이 DB에 존재하는지 확인
		int check = menuService.checkMenu(vo.getMenu_name()); 
		
		try {
			if (check > 0){
				resultMsg = "이미 등록된 메뉴입니다.";
			} else if(check == 0){
				menuService.MenuInsert(vo);

				resultMsg = "등록완료";
			}
		} catch(Exception e){
			e.printStackTrace();
			log.error(e.toString());
			resultMsg = "등록실패";
		}
		return resultMsg;		
	}
	
	//식당운영자 _ 메뉴 목록 출력
	@GetMapping("/admin/menuList")
	public String printMenuList(Model model) throws Exception{
		model.addAttribute("viewAll", menuService.printMenuAll());
		return "admin/menu/menuList";
	}
		
	//식당운영자 _ 수정할 메뉴 정보 가져오기
	@RequestMapping(value = "/admin/menuUpdate", method = RequestMethod.POST)
	public ModelAndView updateForm(@RequestParam("menu_id") String menu_id) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		MenuDTO dto = menuService.getUpdateMenu(menu_id);
		
		mv.addObject("result", dto);
	
		return mv;
	}
		
	//식당운영자 _ 메뉴 수정 저장
	@RequestMapping(value = "/admin/menuUpdateSave", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String MenuupdateSave(MenuDTO dto){	
		String resultMsg = "";
				
		try {
			menuService.UpdateMenu(dto);
			resultMsg = "수정완료";
		} catch(Exception e){
			e.printStackTrace();
			log.error(e.toString());
			resultMsg = "수정실패";
		}
		return resultMsg;	
	}

	//식당운영자 _ 메뉴 삭제
	@RequestMapping(value = "/admin/menuDelete", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String delete(@RequestParam("menu_id") String menu_id){
		String resultMsg = "";
		
		try {
			menuService.DeleteMenu(menu_id);				
			resultMsg = "삭제완료";
		} catch(Exception e){
			e.printStackTrace();
			log.error(e.toString());
			resultMsg = "삭제실패";
		}
		
		return resultMsg;	
	}
}
