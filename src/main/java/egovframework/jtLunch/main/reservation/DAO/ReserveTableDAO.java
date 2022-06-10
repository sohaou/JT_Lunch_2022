package egovframework.jtLunch.main.reservation.DAO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.main.reservation.DTO.ReserveTableDTO;

@Repository("reserveTableDAO")
public class ReserveTableDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public ReserveTableDTO firstTime() {
		return sqlSession.selectOne("tableTime.firstTime");
	}
	
	public ReserveTableDTO secondTime() {
		return sqlSession.selectOne("tableTime.secondTime");
	}
	
	public ReserveTableDTO thirdTime() {
		return sqlSession.selectOne("tableTime.thirdTime");
	}
	
	public ReserveTableDTO fourthTime() {
		return sqlSession.selectOne("tableTime.fourthTime");
	}
}
