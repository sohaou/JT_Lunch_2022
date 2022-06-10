package egovframework.jtLunch.admin.cmmn.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.cmmn.DTO.DailyEatCountDTO;

@Repository("AdminDailyEatCountDAO")
public class AdminDailyEatCountDAO {
	@Inject
	SqlSession sqlSession;
	
	// 체인점별 금일 식사자 수 저장
	public int InsertdailyEatCount(DailyEatCountDTO dto) {
		return sqlSession.insert("dailyEatCount.InsertdailyEatCount",dto);
	}
	// 체인점별 전날 식사자 수 출력
	public List<DailyEatCountDTO> printDailyEatCount(String eatDate){
		return sqlSession.selectList("dailyEatCount.PrintDailyEatCount", eatDate);
	}
	// 검색기간, 체인점별 식사자 수 검색
	public List<DailyEatCountDTO> searchDailyEatCount(String id, String startDate, String endDate) {
		Map <String, String> data = new HashMap<>();
		data.put("franchiseID", id);
		data.put("startDate", startDate);
		data.put("endDate", endDate);
		
		return sqlSession.selectList("dailyEatCount.SearchDailyEatCount", data);
	}
}
