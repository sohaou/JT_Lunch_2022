package egovframework.jtLunch.admin.cmmn.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.jtLunch.admin.cmmn.DTO.FranchiseDTO;
import egovframework.jtLunch.admin.cmmn.Service.AdminFranchiseService;
import egovframework.jtLunch.cmmn.PagingDTO;

@Controller
public class AdminFranchiseController {
	
	private static Logger log = LoggerFactory.getLogger(AdminFranchiseController.class);	
	
	@Autowired
	private AdminFranchiseService franchiseService;
	
	@RequestMapping(value = "/admin/franchiseList", method = RequestMethod.GET)
	public String franchiseList() throws Exception{
		//model.addAttribute("franchiseAll", franchiseService.printAllFranchise());
		// 체인점 관리 페이지 이동
		return "admin/franchise/franchiseList";
	}
	
	// 체인점 아이디 중복확인
	@RequestMapping(value = "/admin/franchiseIDCheck", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String UserIDCheck(@RequestParam("inputId") String inputID){
		String msg = "";
		
		try {
			int check = franchiseService.idCheck(inputID); 
			System.out.println(franchiseService.idCheck(inputID));
			if(check > 0) {
				msg = "이미 사용중인 아이디입니다.";
			}
		} catch (Exception e) {
			msg = "조회 실패";
		}
		return msg;
	}
	
	// 체인점 추가
	@RequestMapping(value = "/admin/franchiseInsert", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String FranchiseInsert(FranchiseDTO dto, HttpServletRequest re) {
		String msg = "";
				
		dto.setTEL("010-" + re.getParameter("phone1") + "-" + re.getParameter("phone2"));
		dto.setDATA_URL(dto.getDATA_URL() + ":" + dto.getPORT_NUM());
		
		try {
			franchiseService.franchiseInsert(dto);
			msg = "등록 완료";
		} catch(Exception e) {
			e.printStackTrace();
			log.error("체인점 등록 오류 : " + e.getMessage());
			System.out.println(e.getMessage());
			msg = "등록 실패";
		}
		return msg;
	}
	
	// 체인점 상세보기
	@RequestMapping(value = "/admin/franchiseDetail", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public ModelAndView franchiseDetail(@RequestParam("id") String ID) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");

		FranchiseDTO dto = franchiseService.franchiseDetail(ID);
		
		String dataUrl = dto.getDATA_URL();
		int idx = dataUrl.indexOf(":");
		
		dto.setDATA_URL(dataUrl.substring(0,idx));
		
		mv.addObject("result", dto);
		return mv;
	}
	
	// 체인점 수정
	@RequestMapping(value = "/admin/franchiseUpdate", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String franchiseUpdate(FranchiseDTO dto, HttpServletRequest re) {
		String msg = "";
		
		dto.setTEL("010-" + re.getParameter("phone1") + "-" + re.getParameter("phone2"));
		dto.setDATA_URL(dto.getDATA_URL() + ":" + dto.getPORT_NUM());
		
		try {
			franchiseService.franchiseUpdate(dto);
			msg = "수정 완료";
		} catch(Exception e) {
			log.error("체인점 수정 오류 : " + e.getMessage());
			System.out.println(e.getMessage());
			msg = "수정 실패";
		}
		return msg;
	}
	
	// 체인점 삭제
	@RequestMapping(value = "/admin/franchiseDelete", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String franchiseDelete(@RequestParam("id") String ID) {
		String msg = "";
		
		try {
			franchiseService.franchiseDelete(ID);
			msg = "삭제 완료";
		} catch(Exception e) {
			log.error("체인점 삭제 오류 : " + e.getMessage());
			System.out.println(e.getMessage());
			msg = "삭제 실패";
		}
		return msg;
	}
	
	// 체인점 검색
	@RequestMapping(value = "/admin/franchiseSearch", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView franchiseSearch(@RequestParam Map<String, Object> searchData) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		List<FranchiseDTO> franchise = new ArrayList<>();
		
		String nowPage = (String) searchData.get("nowPage");
		String cntPerPage = (String) searchData.get("cntPerPage");
		
		try {
			int total = franchiseService.countFranchise();
			// 현재 페이지 정보와 데이터 출력 수에 대한 정보가 없을 때,
			// 현재 페이지는 1페이지로
			// 데이터 출력 수는 10개로 기본 설정
			try {
				PagingDTO vo = new PagingDTO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
				log.info("paging : " + vo);
				mv.addObject("paging", vo);
				searchData.put("startNum", vo.getStart());
				log.info(">>>>>>>> start : " + vo.getStart());
				searchData.put("rpp", vo.getCntPerPage());
			} catch(Exception e) {
				e.printStackTrace();
			}
			franchise = franchiseService.franchiseSearch(searchData);
		} catch (Exception e) {
			log.error("Franchise Search Error : " + e.getMessage());
		}
		
		for(int i = 0; i < franchise.size(); i++) {
			// 주소를 (우편번호) 주소 + 상세주소 로 표현하기 위해
			String address = "(" + franchise.get(i).getZIP_CODE() + ") " + franchise.get(i).getADDRESS() + " " + franchise.get(i).getDETAIL_ADDRESS();
			franchise.get(i).setADDRESS(address);
			// 분류 0:일반 , 1:고급
			if(franchise.get(i).getTYPE() == 0) {
				franchise.get(i).setTYPEstr("일반");
			} else if(franchise.get(i).getTYPE() == 1) {
				franchise.get(i).setTYPEstr("고급");
			}
		}

		mv.addObject("data",franchise);
		mv.addObject("total", franchiseService.countFranchise());
		
		return mv;
	}
}
