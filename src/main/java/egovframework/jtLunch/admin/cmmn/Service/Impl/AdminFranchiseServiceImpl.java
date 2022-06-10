package egovframework.jtLunch.admin.cmmn.Service.Impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.admin.cmmn.DAO.AdminFranchiseDAO;
import egovframework.jtLunch.admin.cmmn.DTO.FranchiseDTO;
import egovframework.jtLunch.admin.cmmn.Service.AdminFranchiseService;

@Service("AdminFranchiseService")
public class AdminFranchiseServiceImpl implements AdminFranchiseService{
	@Inject
	private AdminFranchiseDAO dao;
	
	// DB에 저장되어 있는 모든 체인점 출력
	@Override
	public List<FranchiseDTO> printAllFranchise() throws Exception{
		return dao.printAllFranchise();
	}	
	// DB에 저장되어 있는 모든 체인점 수
	@Override
	public int countFranchise() throws Exception{
		return dao.countFranchise();
	}	
	// 체인점 아이디 중복 확인
	public int idCheck(String inputID) throws Exception{
		return dao.idCheck(inputID);
	}
	// DB에 체인점 정보 추가
	@Override
	public int franchiseInsert(FranchiseDTO dto) throws Exception{
		return dao.franchiseInsert(dto);
	}	
	// 체인점 상세보기
	@Override
	public FranchiseDTO franchiseDetail(String id) throws Exception{
		return dao.franchiseDetail(id);
	}	
	// 체인점 정보 수정
	@Override
	public int franchiseUpdate(FranchiseDTO dto) throws Exception{
		return dao.franchiseUpdate(dto);
	}	
	// 체인점 삭제
	@Override     
	public int franchiseDelete(String id) throws Exception{
		return dao.franchiseDelete(id);
	}	
	// 체인점 검색
	@Override
	public List<FranchiseDTO> franchiseSearch(Map <String, Object> searchData) throws Exception{
		return dao.franchiseSearch(searchData);
	}
}
