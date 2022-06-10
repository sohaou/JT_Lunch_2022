package egovframework.jtLunch.admin.cmmn.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.cmmn.DTO.FranchiseDTO;

@Repository("AdminFranchiseDAO")
public class AdminFranchiseDAO {
	@Inject
	SqlSession sqlSession;
	
	// DB에 저장되어 있는 모든 체인점 출력
	public List<FranchiseDTO> printAllFranchise(){
		return sqlSession.selectList("franchise.printAllFranchise");
	}
	
	// DB에 저장되어 있는 체인점수
	public int countFranchise() {
		return sqlSession.selectOne("franchise.countFranchise");
	}
	
	// 체인점 아이디 중복 확인
	public int idCheck(String inputID) {
		return sqlSession.selectOne("franchise.idCheck", inputID);
	}
	
	// DB에 체인점 정보 저장
	public int franchiseInsert(FranchiseDTO dto) {
		return sqlSession.insert("franchise.franchiseInsert", dto);
	}

	// 체인점 상세보기
	public FranchiseDTO franchiseDetail(String id){
		return sqlSession.selectOne("franchise.franchiseDetail", id);
	}
	
	// 체인점 정보 수정
	public int franchiseUpdate(FranchiseDTO dto) {
		return sqlSession.update("franchise.franchiseUpdate", dto);
	}
	
	// 체인점 삭제
	public int franchiseDelete(String id) {
		return sqlSession.delete("franchise.franchiseDelete", id);
	}
	
	// 체인점 검색
	public List<FranchiseDTO> franchiseSearch(Map <String, Object> searchData){
		return sqlSession.selectList("franchise.franchiseSearch", searchData);
	}
}
