package egovframework.jtLunch.admin.cmmn.Service;

import java.util.List;
import java.util.Map;

import egovframework.jtLunch.admin.cmmn.DTO.FranchiseDTO;

public interface AdminFranchiseService {
	// DB에 저장되어 있는 모든 체인점 출력
	public List<FranchiseDTO> printAllFranchise() throws Exception;
	// DB에 저장되어 있는 모든 체인점 수
	public int countFranchise() throws Exception;
	// 체인점 아이디 중복 확인
	public int idCheck(String inputID) throws Exception;
	// DB에 체인점 정보 추가
	public int franchiseInsert(FranchiseDTO dto) throws Exception;
	// 체인점 상세보기
	public FranchiseDTO franchiseDetail(String id)throws Exception;
	// 체인점 정보 수정
	public int franchiseUpdate(FranchiseDTO dto) throws Exception;
	// 체인점 삭제
	public int franchiseDelete(String id) throws Exception;
	// 체인점 검색
	public List<FranchiseDTO> franchiseSearch(Map <String, Object> searchData) throws Exception;
}
