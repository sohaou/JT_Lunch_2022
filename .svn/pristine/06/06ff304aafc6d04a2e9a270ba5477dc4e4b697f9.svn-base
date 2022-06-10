package egovframework.jtLunch.admin.owner.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.admin.owner.DTO.MenuPlanDTO;

@Repository("OwnerMenuPlanDAO")
public class OwnerMenuPlanDAO {
	@Inject
	SqlSession sqlSession;

	//식당 운영자 _ 식단 등록
	public int insertTodayMenu(MenuPlanDTO dto) throws Exception{
	return sqlSession.insert("menuPlan.insertTodayMenu",dto);
	}
	//식당 운영자 _ 식단 출력
	public MenuPlanDTO todayMenuPlan() throws Exception{
	return sqlSession.selectOne("menuPlan.todayMenuPlan");
	}
	//식당 운영자 _ 식단 수정
	public int UpdateMenuPlan(MenuPlanDTO dto) throws Exception{
	return sqlSession.update("menuPlan.UpdateMenuPlan",dto);
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
	// TODO Auto-generated method stub
	return sqlSession.selectList("menuPlan.selectDayMenuPlan",dateData);
	}
	//식당 운영자 _ 일별 식단 출력 (선택 날짜 출력)
	public MenuPlanDTO SelectDateMenuPlan(String select_date) throws Exception{
	// TODO Auto-generated method stub
	return sqlSession.selectOne("menuPlan.SelectDateMenuPlan",select_date);
	}
	//식당 운영자 _ 선택한 날짜에 식단의 존재 유무 확인
	public int checkMenuIn(String select_date) {
	// TODO Auto-generated method stub
	return sqlSession.selectOne("menuPlan.checkMenuIn",select_date);
	}

}
