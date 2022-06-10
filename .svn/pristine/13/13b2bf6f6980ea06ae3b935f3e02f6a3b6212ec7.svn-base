package egovframework.jtLunch.admin.owner.Service.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.admin.owner.DAO.OwnerMenuPlanDAO;
import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.admin.owner.DTO.MenuPlanDTO;
import egovframework.jtLunch.admin.owner.Service.OwnerMenuPlanService;

@Service("OwnerMenuPlanService")
public class OwnerMenuPlanServiceImpl implements OwnerMenuPlanService{
	@Inject
	private OwnerMenuPlanDAO dao;
	
	// 식당 운영자 _식단 등록
	@Override
	public int insertTodayMenu(MenuPlanDTO dto) throws Exception{
		return dao.insertTodayMenu(dto);
	}
	//식당 운영자 _ 식단 출력
	@Override
	public MenuPlanDTO todayMenuPlan() throws Exception{
		return dao.todayMenuPlan();
	}
	//식당 운영자 _식단 수정
	@Override
	public int UpdateMenuPlan(MenuPlanDTO dto) throws Exception{
		return dao.UpdateMenuPlan(dto);
	}
	//식당 운영자 _ 금일 예약자 수
	@Override
	public int countReserve() throws Exception{
		return dao.countReserve();
	}
	//식당 운영자 _ 금일 실 식사자 출력
	@Override
	public int countAte_user() throws Exception{
		return dao.countAte_user();
	}
	//식당 운영자 _ 달력에서 한달 식단 출력 (시작일자 ~ 끝일자)
	@Override
	public List<MenuPlanDTO> selectDayMenuPlan(DateData dateData) throws Exception{
		return dao.selectDayMenuPlan(dateData);
	}
	//식당 운영자 _ 일별 식단 출력 (선택 날짜 출력)
	@Override
	public MenuPlanDTO SelectDateMenuPlan(String select_date) throws Exception{
		return dao.SelectDateMenuPlan(select_date);
	}
	//식당운영자_선택한 날짜에 식단이 등록되어 있는지 체크
	@Override
	public int checkMenuIn(String select_date) throws Exception{ 
		return dao.checkMenuIn(select_date);
	}	
}
