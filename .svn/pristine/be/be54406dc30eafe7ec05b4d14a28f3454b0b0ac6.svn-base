package egovframework.jtLunch.main.reservation.Service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.cmmn.security.DTO.CustomUserDetails;
import egovframework.jtLunch.main.reservation.DAO.ReservationDAO;
import egovframework.jtLunch.main.reservation.DTO.DivisionDTO;
import egovframework.jtLunch.main.reservation.DTO.ReservationDTO;
import egovframework.jtLunch.main.reservation.DTO.ReservationQRDTO;
import egovframework.jtLunch.main.reservation.Service.ReservationService;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService{
	
	@Resource(name="reservationDAO")
	private ReservationDAO reservationDAO;
	
	@Override
	public void reserve(ReservationDTO reservationDTO) {
		reservationDAO.reserve(reservationDTO);
	}

	@Override
	public ReservationDTO selectUser(String id) {
		return reservationDAO.selectUser(id);
	}

	@Override
	public List<DivisionDTO> selectDepartment() {
		return reservationDAO.selectDepartment();
	}

	@Override
	public List<DivisionDTO> selectTeam() {
		return reservationDAO.selectTeam();
	}

	@Override
	public List<Map<String, Object>> reserveSelectUser(Map<String, Object> requestMap){
		return reservationDAO.reserveSelectUser(requestMap);
	}
	
	@Override
	public Map<String, Object> selectTable(Map<String, Object> requestMap){
		return reservationDAO.selectTable(requestMap);
	}

	@Override
	public ReservationQRDTO selectQrUser(String id) {
		return reservationDAO.selectQrUser(id);
	}

	@Override
	public CustomUserDetails selectMember(String id) {
		return reservationDAO.selectMember(id);
	}

	@Override
	public int updateTableCount(Map<String, Object> tableMap) {
		return reservationDAO.updateTableCount(tableMap);
	}

	@Override
	public Map<String, Object> addMember(Map<String, Object> requestMap){
		return reservationDAO.addMember(requestMap);
	}

	@Override
	public List<CustomUserDetails> memberAllPrint() {
		return reservationDAO.memberAllPrint();
	}
	
}
