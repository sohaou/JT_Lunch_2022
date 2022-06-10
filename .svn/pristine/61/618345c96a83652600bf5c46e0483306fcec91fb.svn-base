package egovframework.jtLunch.admin.owner.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import egovframework.jtLunch.admin.owner.DTO.MenuDTO;
import egovframework.jtLunch.admin.owner.Service.MenuService;

@Controller
public class OwnerMenuController {
	
	@Autowired
	private MenuService menuService;
	
	//식당운영자 _ 저장되어 있는 모든 메뉴 정보 가져오기
	@PostMapping("/owner/menuPlanner")
	public ModelAndView menuPlanner() throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		List<MenuDTO> category = menuService.printMenuAll();
		mv.addObject("category", category);
				
		return mv;
	}
	
	//식당운영자 메뉴 등록 내용 저장
	@RequestMapping(value="/owner/menuSave", method=RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String MenuSave(HttpServletRequest re) throws Exception{
		String resultMsg = "";
			
		String menuId = re.getParameter("menuId");
		String type = re.getParameter("selectType");
		String menuName = re.getParameter("menuName");
			
		MenuDTO vo = new MenuDTO();
			
		if(type.equals("밥")) {
			menuId = "R-" + menuId;
		} else if(type.equals("국")) {
			menuId = "S-" + menuId;
		} else if(type.equals("반찬")) {
			menuId = "D-" + menuId;
		}
			
		//입력한 메뉴명이 DB에 존재하는지 확인
		int check = menuService.checkMenu(menuName); 
		
		try {
			if(check > 0) {
				resultMsg = "이미 등록된 메뉴입니다.";
			} else if(check == 0) {
				vo.setMenu_id(menuId);
				vo.setMenu_type(type);
				vo.setMenu_name(menuName);
					
				menuService.MenuInsert(vo);

				resultMsg = "등록완료";
			}
		} catch(Exception e){
			resultMsg = "등록실패";
		}
		return resultMsg;		
	}
	
	//식당운영자 _ 메뉴 목록 출력
	@GetMapping("/owner/menuList")
	public String printMenuList(Model model) throws Exception{
		model.addAttribute("viewAll", menuService.printMenuAll());
		return "admin/owner/menu/menuList";
	}
		
	//식당운영자 _ 수정할 메뉴 정보 가져오기
	@RequestMapping(value = "/owner/menuUpdate", method = RequestMethod.POST)
	public ModelAndView updateForm(@RequestParam("menu_id") String menu_id) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		MenuDTO dto = menuService.getUpdateMenu(menu_id);
		
		mv.addObject("result", dto);
	
		return mv;
	}
		
	//식당운영자 _ 메뉴 수정 저장
	@RequestMapping(value = "/owner/updateSave", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String MenuupdateSave(MenuDTO dto){	
		String resultMsg = "";
				
		try {
			menuService.UpdateMenu(dto);
			resultMsg = "수정완료";
		} catch(Exception e){
			resultMsg = "수정실패";
		}
		return resultMsg;	
	}

	//식당운영자 _ 메뉴 삭제
	@RequestMapping(value = "/owner/menuDelete", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String delete(@RequestParam("menu_id") String menu_id){
		String resultMsg = "";
		
		try {
			menuService.DeleteMenu(menu_id);				
			resultMsg = "삭제완료";
		} catch(Exception e){
			resultMsg = "삭제실패";
		}
		
		return resultMsg;	
	}
}
