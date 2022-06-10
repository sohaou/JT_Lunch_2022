package egovframework.jtLunch.admin.owner.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.admin.owner.DTO.MenuPlanDTO;
import egovframework.jtLunch.admin.owner.DTO.SideMenuDTO;

@Repository("OwnerMenuPlanDAO")
public class OwnerMenuPlanDAO {
	@Inject
	SqlSession sqlSession;

	//식당 운영자 _ 식단 등록
	public int insertTodayMenu(MenuPlanDTO dto) throws Exception{
		return sqlSession.insert("menuPlan.insertTodayMenu",dto);
	}
	//식당 운영자 _ 반찬 등록
	public int insertTodayMenuSide(SideMenuDTO dto) throws Exception{
		return sqlSession.insert("menuPlan.insertTodayMenuSide",dto);
	}
	//식당 운영자 _ 금일 식단 출력
	public MenuPlanDTO todayMenuPlan() throws Exception{
		return sqlSession.selectOne("menuPlan.todayMenuPlan");
	}
	//식당 운영자 _ 금일 식단 반찬 출력
	public List<SideMenuDTO> todayMenuPlanSide() throws Exception{
		return sqlSession.selectList("menuPlan.todayMenuPlanSide");
	}
	//식당 운영자 _ 식단 수정
	public int UpdateMenuPlan(MenuPlanDTO dto) throws Exception{
		return sqlSession.update("menuPlan.UpdateMenuPlan",dto);
	}
	//식당 운영자 _ 식단 수정(반찬)
	public int DeleteMenuPlanSide(String select_date) throws Exception{
		return sqlSession.delete("menuPlan.DeleteMenuPlanSide",select_date);
	}
	//금일 예약자 수 출력
	public int countReserve() {
		return sqlSession.selectOne("menuPlan.countReserve");
	}
	//금일 실 식사자 수 출력
	public int countAte_user() {
		return sqlSession.selectOne("menuPlan.countAte_user");
	}
	//식당 운영자 _ 일별 식단 출력 ( 기간 지정 출력 )
	public List<MenuPlanDTO> selectDayMenuPlan(DateData dateData) throws Exception{
		return sqlSession.selectList("menuPlan.selectDayMenuPlan",dateData);
	}
	//식당 운영자 _ 일별 식단 반찬 출력 ( 기간 지정 출력 )
	public List<SideMenuDTO> MonthMenuPlanSide(DateData dateData) throws Exception{
		return sqlSession.selectList("menuPlan.MonthMenuPlanSide",dateData);
	}
	//식당 운영자 _ 일별 식단 출력 (선택 날짜 출력)
	public MenuPlanDTO SelectDateMenuPlan(String select_date) throws Exception{
		return sqlSession.selectOne("menuPlan.SelectDateMenuPlan",select_date);
	}
	//식당 운영자 _ 일별 식단 반찬 출력 (선택 날짜 출력)
	public List<SideMenuDTO> SelectDateMenuPlanSide(String select_date) throws Exception{
		return sqlSession.selectList("menuPlan.SelectDateMenuPlanSide",select_date);
	}
	//식당 운영자 _ 선택한 날짜에 식단의 존재 유무 확인
	public int checkMenuIn(String select_date) {
		return sqlSession.selectOne("menuPlan.checkMenuIn",select_date);
	}
	
	//식당 운영자 _ 전체 식단 출력_2
	public List<MenuPlanDTO> dailyDietAll(DateData dateData) throws Exception{
		return sqlSession.selectList("menuPlan.dailyDietAll", dateData);
	}

}
