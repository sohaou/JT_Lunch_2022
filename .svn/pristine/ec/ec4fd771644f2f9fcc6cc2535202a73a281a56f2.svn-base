package egovframework.jtLunch.main.reservation.Service;

import java.util.List;
import java.util.Map;

import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;
import egovframework.jtLunch.main.reservation.DTO.DivisionDTO;
import egovframework.jtLunch.main.reservation.DTO.ReservationDTO;
import egovframework.jtLunch.main.reservation.DTO.ReservationQRDTO;

public interface ReservationService {
	
	void reserve(ReservationDTO resertvationDTO);
	
	ReservationDTO selectUser(String id);
	
	ReservationQRDTO selectQrUser(String id);

	List<DivisionDTO> selectDepartment();
	
	List<DivisionDTO> selectTeam();

	List<Map<String, Object>> reserveSelectUser(Map<String, Object> requestMap);
	
	Map<String, Object> selectTable(Map<String, Object> requestMap);
	
	CustomUserDetails selectMember(String id);
	
	int updateTableCount(Map<String, Object> tableMap);
	
	Map<String, Object> addMember(Map<String, Object> requestMap);
	
	List<CustomUserDetails> memberAllPrint();

}
