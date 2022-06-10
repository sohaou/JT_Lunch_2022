package egovframework.jtLunch.main.request.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.dashboard.DTO.DashBoardDTO;
import egovframework.jtLunch.main.request.DTO.UserRequestDTO;

@Repository("UserRequestDAO")
public class UserRequestDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<UserRequestDTO> UserRequestPrintAll() throws Exception{
		return sqlSession.selectList("UserRequest.UserRequestPrintAll");
	}
	public int UserRequestInsert(UserRequestDTO dto) throws Exception{
		return sqlSession.insert("UserRequest.UserRequestInsert", dto);
	}
	public UserRequestDTO UserRequestDetail(int req_id) throws Exception{
		return sqlSession.selectOne("UserRequest.UserRequestDetail", req_id);
	}
	public int UserRequestUpdate(UserRequestDTO dto) throws Exception{
		return sqlSession.update("UserRequest.UserRequestUpdate", dto);
	}
	public int UserRequestDelete(int req_id) throws Exception{
		return sqlSession.delete("UserRequest.UserRequestDelete", req_id);
	}
	public int UserCountUpdate() throws Exception{
		return sqlSession.update("UserRequest.UserCountUpdate"); 
	} 
	public int UserCountSet() throws Exception{
		return sqlSession.update("UserRequest.UserCountSet"); 
	}
	public DashBoardDTO UserLoginName(String user_id) throws Exception{
		return sqlSession.selectOne("UserRequest.UserLoginName",user_id); 
	}
}
