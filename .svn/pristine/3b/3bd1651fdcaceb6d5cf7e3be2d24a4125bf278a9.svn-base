package egovframework.jtLunch.admin.cmmn.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.cmmn.DTO.CmmnRequestDTO;

@Repository("CmmnRequestDAO")
public class CmmnRequestDAO {
	@Inject
	SqlSession sqlSession;
	
	//요청사항 출력
	public List<CmmnRequestDTO> printRequestAll(){
		// TODO Auto-generated method stub
		return sqlSession.selectList("CmmnRequest.printRequestAll");
	}
	//요청사항 상세보기
	public CmmnRequestDTO selectRequest(String req_id){
		// TODO Auto-generated method stub
		return sqlSession.selectOne("CmmnRequest.selectRequest",req_id);
	}
	//식당운영자 요청사항 확인 업데이트
	public int ownerCheck(String req_id) throws Exception{
		return sqlSession.update("CmmnRequest.ownerCheck",req_id);
	}
	//관리자 요청사항 확인 업데이트
	public int adminCheck(String req_id) throws Exception{
		return sqlSession.update("CmmnRequest.adminCheck",req_id);
	}	
}
