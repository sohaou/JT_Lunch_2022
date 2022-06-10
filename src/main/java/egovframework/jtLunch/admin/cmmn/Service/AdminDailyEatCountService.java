package egovframework.jtLunch.admin.cmmn.Service;

import java.util.List;

import egovframework.jtLunch.admin.cmmn.DTO.DailyEatCountDTO;

public interface AdminDailyEatCountService {
	// 체인점(일반/고급) 금일 식사자 수 정보 저장
	public int InsertdailyEatCount(DailyEatCountDTO dto) throws Exception;
	
	
	// 체인점별 전날 식사자 수 출력
	public List<DailyEatCountDTO> printDailyEatCount(String eatDate) throws Exception;
	// 검색기간, 체인점별 식사자 수 검색
	public List<DailyEatCountDTO> searchDailyEatCount(String id, String startDate, String endDate) throws Exception;
}
