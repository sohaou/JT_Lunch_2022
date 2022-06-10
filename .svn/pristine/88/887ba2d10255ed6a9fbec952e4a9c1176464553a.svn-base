package egovframework.jtLunch.main.menuplanner.DAO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.main.menuplanner.DTO.MenuPlannerDTO;

@Repository("menuplannerDAO")
public class MenuPlannerDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public MenuPlannerDTO selectmenu() {
		return sqlSession.selectOne("menuplanner.selectmenu");
	}
	
	public int likecountrice(String steamed_rice) {
		return sqlSession.update("menuplanner.likeCountSteamedRice", steamed_rice);
	}
	
	public int likecountsoup(String soup) {
		return sqlSession.update("menuplanner.likeCountSoup", soup);
	}
	
	public int likecountsidedish(String side_dish) {
		return sqlSession.update("menuplanner.likeCountSideDish", side_dish);
	}
	
	public int insertTodayMenu(MenuPlannerDTO dto) {
		return sqlSession.insert("menuplanner.insertTodayMenu",dto);
	}
}
