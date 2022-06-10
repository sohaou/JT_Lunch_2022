package egovframework.jtLunch.admin.owner.Service;

import java.util.List;

import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.admin.owner.DTO.MenuPlanDTO;
import egovframework.jtLunch.admin.owner.DTO.SideMenuDTO;

public interface OwnerMenuPlanService {
	// 식당 운영자 _식단 등록
	public int insertTodayMenu(MenuPlanDTO dto) throws Exception;
	// 식당 운영자 _식단 등록(반찬 등록)
	public int insertTodayMenuSide(SideMenuDTO dto) throws Exception;
	//식당 운영자 _ 식단 출력
	public MenuPlanDTO todayMenuPlan() throws Exception;
	//식당 운영자 _ 금일 식단 반찬 출력
	public List<SideMenuDTO> todayMenuPlanSide() throws Exception;
	//식당 운영자 _식단 수정
	public int UpdateMenuPlan(MenuPlanDTO dto) throws Exception;
	//식당 운영자 _식단 수정(반찬)
	public int DeleteMenuPlanSide(String select_date) throws Exception;
	//식당 운영자 _ 금일 예약자 수
	public int countReserve() throws Exception; 
	//식당 운영자 _ 금일 실 식사자 출력
	public int countAte_user() throws Exception;
	//식당 운영자 _ 달력에서 한달 식단 출력 (시작일자 ~ 끝일자)
	public List<MenuPlanDTO> selectDayMenuPlan(DateData dateData) throws Exception;
	//식당 운영자 _ 달력에서 한달 식단 반찬 출력 (시작일자 ~ 끝일자)
	public List<SideMenuDTO> MonthMenuPlanSide(DateData dateData) throws Exception;
	//식당 운영자 _ 일별 식단 출력 (선택 날짜 출력)
	public MenuPlanDTO SelectDateMenuPlan(String select_date) throws Exception;
	//식당 운영자 _ 일별 식단 반찬 출력 (선택 날짜 출력)
	public List<SideMenuDTO> SelectDateMenuPlanSide(String select_date) throws Exception;
	//식당운영자_선택한 날짜에 식단이 등록되어 있는지 체크
	public int checkMenuIn(String select_date) throws Exception; 
	//식당 운영자 _ 전체 식단 출력_2
	public List<MenuPlanDTO> dailyDietAll(DateData dateData) throws Exception;
}
