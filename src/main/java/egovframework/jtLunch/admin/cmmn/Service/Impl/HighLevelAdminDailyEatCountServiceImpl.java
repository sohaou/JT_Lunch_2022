package egovframework.jtLunch.admin.cmmn.Service.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.admin.cmmn.DAO.AdminDailyEatCountDAO;
import egovframework.jtLunch.admin.cmmn.DTO.DailyEatCountDTO;
import egovframework.jtLunch.admin.cmmn.Service.AdminDailyEatCountService;

@Service("HighLevelService")
public class HighLevelAdminDailyEatCountServiceImpl implements AdminDailyEatCountService{
	@Inject
	private AdminDailyEatCountDAO dao;
	
	// 체인점별 식사자 수 저장_고급 체인점
	// 식사자수 2배
	@Override
	public int InsertdailyEatCount(DailyEatCountDTO dto) throws Exception{
		int count= dto.getCOUNT_EAT() * 2;
		dto.setCOUNT_EAT(count);
		return dao.InsertdailyEatCount(dto);
	}

	@Override
	public List<DailyEatCountDTO> printDailyEatCount(String eatDate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DailyEatCountDTO> searchDailyEatCount(String id, String startDate, String endDate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
