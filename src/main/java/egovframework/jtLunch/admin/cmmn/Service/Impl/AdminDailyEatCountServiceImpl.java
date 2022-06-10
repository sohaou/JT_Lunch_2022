package egovframework.jtLunch.admin.cmmn.Service.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.admin.cmmn.DAO.AdminDailyEatCountDAO;
import egovframework.jtLunch.admin.cmmn.DTO.DailyEatCountDTO;
import egovframework.jtLunch.admin.cmmn.Service.AdminDailyEatCountService;

@Service("service1")
public class AdminDailyEatCountServiceImpl implements AdminDailyEatCountService{
	@Inject
	private AdminDailyEatCountDAO dao;
		
	// 체인점별 식사자 수 저장_일반 체인점
	@Override
	public int InsertdailyEatCount(DailyEatCountDTO dto) throws Exception{
		return dao.InsertdailyEatCount(dto);
	}
	// 체인점별 전날 식사자 수 출력
	@Override
	public List<DailyEatCountDTO> printDailyEatCount(String eatDate) throws Exception{
		return dao.printDailyEatCount(eatDate);
	}
	// 검색기간, 체인점별 식사자 수 검색
	public List<DailyEatCountDTO> searchDailyEatCount(String id, String startDate, String endDate) throws Exception{
		return dao.searchDailyEatCount(id, startDate, endDate);
	}
}