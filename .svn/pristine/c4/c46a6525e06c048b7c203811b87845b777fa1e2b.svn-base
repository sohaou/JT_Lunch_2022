package egovframework.jtLunch.main.reservation.DAO;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;
import egovframework.jtLunch.main.reservation.DTO.DivisionDTO;
import egovframework.jtLunch.main.reservation.DTO.ReservationDTO;
import egovframework.jtLunch.main.reservation.DTO.ReservationQRDTO;

@Repository("reservationDAO")
public class ReservationDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public void reserve(ReservationDTO reservationDTO) {
		sqlSession.insert("reserve.reserve", reservationDTO);
	}
	
	public ReservationDTO selectUser(String id) {
		return sqlSession.selectOne("reserve.selectUser", id);
	}
	
	public ReservationQRDTO selectQrUser(String id) {
		return sqlSession.selectOne("reserve.selectQrUser", id);
	}

	public List<DivisionDTO> selectDepartment() {
		return sqlSession.selectList("division.selectDepartment");
	}
	
	public List<DivisionDTO> selectTeam(){
		return sqlSession.selectList("division.selectTeam");
	}
	public List<Map<String, Object>> reserveSelectUser(Map<String, Object> requestMap){
		return sqlSession.selectList("division.selectUser", requestMap);
	}
	
	public Map<String, Object> selectTable(Map<String, Object> requestMap) {
		return sqlSession.selectOne("reserve.selectTable", requestMap);
	}
	
	public CustomUserDetails selectMember(String id) {
		return sqlSession.selectOne("reserve.selectMember", id);
	}
	
	public int updateTableCount(Map<String, Object> tableMap){
		return sqlSession.update("reserve.updateTableCount", tableMap);
	}
	
	public Map<String, Object> addMember(Map<String, Object> requestMap){
		return sqlSession.selectOne("reserve.addMember", requestMap);
	}
	
	public List<CustomUserDetails> memberAllPrint(){
		return sqlSession.selectList("reserve.memberAllPrint");
	}
	
}
 