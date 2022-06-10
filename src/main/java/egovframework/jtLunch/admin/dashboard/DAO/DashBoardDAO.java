package egovframework.jtLunch.admin.dashboard.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.dashboard.DTO.DashBoardDTO;

@Repository("dashBoardDAO")
public class DashBoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	//일별 식수 현황 그래프
	public DashBoardDTO getChartData() throws Exception{
		return sqlSession.selectOne("dashBoard.getChartData");
	}
	//월별 식수 현황 그래프
	public DashBoardDTO getChartMonth() throws Exception{
		return sqlSession.selectOne("dashBoard.getChartMonth");
	}
	//월별 식수자 수 
	public int getmonthAte() throws Exception{
		return sqlSession.selectOne("dashBoard.getmonthAte");
	}
	//사용자 정보 출력
	public List<DashBoardDTO> searchUser() throws Exception{
		return sqlSession.selectList("dashBoard.searchUser");
	}
	//사용자 정보 삭제
	public int deleteUser(String user_id) throws Exception{
		return sqlSession.delete("dashBoard.deleteUser", user_id);
	}
	//식수자 리스트 출력
	public List<DashBoardDTO> printAteUser(DashBoardDTO dto) throws Exception{
		return sqlSession.selectList("dashBoard.printAteUser", dto);
	}
	//사용자 정보 등록
	public int userInsert(DashBoardDTO dto) throws Exception{
		return sqlSession.insert("dashBoard.userInsert", dto);
	}
}
