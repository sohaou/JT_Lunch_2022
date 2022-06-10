package egovframework.jtLunch.admin.dashboard.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.jtLunch.admin.cmmn.UseDateData;
import egovframework.jtLunch.admin.dashboard.DTO.NoticeDTO;
import egovframework.jtLunch.admin.dashboard.Service.NoticeService;
import egovframework.jtLunch.cmmn.PagingDTO;

@Controller
public class NoticeController {
	private static Logger log = LoggerFactory.getLogger(NoticeController.class);	
	
	@Autowired
	private NoticeService noticeService;
	
	//관리자 공지사항
	@RequestMapping(value = "/admin/notice", method = RequestMethod.GET)
	public String notice(NoticeDTO noticedto, Model model) throws Exception {
		model.addAttribute("notice", noticeService.NoticePrint());
		return "admin/notice/notice";
	}
	// 공지사항 검색
	@RequestMapping(value = "/admin/noticeSearch", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public ModelAndView noticeSearch(@RequestParam Map<String, Object> searchData) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		
		String nowPage = (String) searchData.get("nowPage");
		String cntPerPage = (String) searchData.get("cntPerPage");
		
		List<NoticeDTO> notice = new ArrayList<>();

		try {
			int total = 0;
			
			if(searchData.get("searchOption") == null && searchData.get("keyword") == null 
					&& searchData.get("startDate") == null && searchData.get("endDate") == null) {
				log.info(">>>>>>>>>>null");
				total = noticeService.CountNotice();
			} else {
				total = noticeService.NoticeSearchCount(searchData);
			}
			
			//int total = noticeService.CountNotice();
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
				
				notice = noticeService.NoticeSearch(searchData);
				for(int i = 0; i < notice.size(); i++) {
					String date = notice.get(i).getUploadDate();
					notice.get(i).setUploadDate(date.substring(0,10));
				}
			} catch(Exception e) {
				e.printStackTrace();
				log.error(e.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}


		log.info(">>>>>>>>>>페이지 이동 searchData : " + searchData);
		
		mv.addObject("data", notice);
		mv.addObject("total", noticeService.CountNotice());
		return mv;
	}
	// 팝업 설정한 공지사항 가져오기
	@RequestMapping(value = "/cmmn/noticePopup", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public ModelAndView noticePopup() throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		// 오늘 날짜
		String today = new UseDateData().getToday();
		List<NoticeDTO> notice = noticeService.NoticePopup(today);
		
		mv.addObject("result", notice);
		
		return mv;
	}
	
	@RequestMapping(value = "/admin/noticeinsert", method = RequestMethod.GET)
	public String noticeinsert() throws Exception {
		return "admin/notice/noticeinsert";
	}
		
	//공지사항 등록
	@RequestMapping(value = "/admin/noticeInsert", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String noticeInsert(NoticeDTO dto){
		String resultMsg = "";
		System.out.println(dto);
		try {
			if(dto.getPop_up() == 1) {
				noticeService.popUpReset();
				noticeService.NoticeInsert(dto);
			} else {
				noticeService.NoticeInsert(dto);
			}
			resultMsg = "등록완료";
		} catch(Exception e){
			e.printStackTrace();
			log.error(e.toString());
			
			resultMsg = "등록실패";
		}
			
		return resultMsg;	
	}
	
	//공지사항 상세보기
	@RequestMapping(value = "/admin/noticedetails", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public ModelAndView noticedetails(@RequestParam("id") int id){
		ModelAndView mv = new ModelAndView("jsonView");
		NoticeDTO notice = new NoticeDTO();
		
		try {
			notice = noticeService.NoticeSelect(id);
			System.out.println(">>>>>>>>>>>>>>>>>>>>" + notice);
		} catch(Exception e) {
			e.printStackTrace();
			log.error(e.toString());
		}
		
		mv.addObject("result", notice);
		
		return mv;
	}
	//공지사항 수정
	@RequestMapping(value = "/admin/noticeupdate", method = RequestMethod.GET)
	public String noticeupdate(@RequestParam("notice_id") int notice_id, Model model) throws Exception {
		
		model.addAttribute("notice", noticeService.NoticeSelect(notice_id));
		
		return "admin/notice/noticeupdate";
	}
	// 공지사항 수정 저장
	@RequestMapping(value = "/admin/noticeUpdate", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String noticeUpdate(NoticeDTO dto){
		
		String resultMsg = "";
		
		try {
			if(dto.getPop_up() == 1) {
				noticeService.popUpReset();
				noticeService.NoticeUpdate(dto);
			} else {
				noticeService.NoticeUpdate(dto);
			}
			resultMsg = "수정완료";
		} catch(Exception e){
			e.printStackTrace();
			log.error(e.toString());
			resultMsg = "수정실패";
		}
			
		return resultMsg;	
	}
	//공지사항 삭제
	@RequestMapping(value = "/admin/noticeDelete", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String noticeDelete(@RequestParam("notice_id") int notice_id){
		
		String resultMsg = "";
		
		try {
			noticeService.NoticeDelete(notice_id);
			noticeService.CountSet();
			noticeService.CountUpdate();
			noticeService.CountStartNum(noticeService.CountNotice());
			resultMsg = "삭제완료";
		} catch(Exception e){
			resultMsg = "삭제실패";
		}
			
		return resultMsg;	
	}
}
