package egovframework.jtLunch.admin.owner.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.main.qrcode.DTO.QrCodeDTO;

@Repository("CheckAteUserDAO")
public class CheckAteUserDAO {
	@Inject
	SqlSession sqlSession;
	
	// 금일 식사자 수 count
	public int countTodayEat(String eatDate, String id) {
		// TODO Auto-generated method stub
		Map <String, String> data = new HashMap<>();
		data.put("eatDate", eatDate);
		data.put("id", id);
		return sqlSession.selectOne("CheckAteUser.countTodayEat",data);
	}
	// 한달동안의 식사자 수 추력
	public int countAteUser(DateData dateData) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("CheckAteUser.countAteUser",dateData);
	}
	// 한달동안의 식사자 정보 출력
	public List<QrCodeDTO> ateuser_list(DateData dateData){
		// TODO Auto-generated method stub
		return sqlSession.selectList("CheckAteUser.AteUserList",dateData);
	}
	// 선택한 날짜의 식사자 추력
	public List<QrCodeDTO> DayAteUserAll(String ate_date){
		// TODO Auto-generated method stub
		return sqlSession.selectList("CheckAteUser.DayAteUserAll",ate_date);
	}
	// 선택한 날짜의 식사자 수 출력
	public int CountAteUserDate(String ate_date) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("CheckAteUser.CountAteUserDate",ate_date);
	}
	// 월에서 식사자가 있는 날 출력
	public List<Map<String,String>> AteUserInMonth(DateData dateData) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("CheckAteUser.AteUserInMonth",dateData);
	}
}
