package egovframework.jtLunch.main.reservation.Service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.main.reservation.DAO.ReserveTableDAO;
import egovframework.jtLunch.main.reservation.DTO.ReserveTableDTO;
import egovframework.jtLunch.main.reservation.Service.ReserveTableService;

@Service("reserveTableService")
public class ReserveTableServiceImpl implements ReserveTableService{

	@Resource(name="reserveTableDAO")
	private ReserveTableDAO reserveTableDAO;
	
	@Override
	public ReserveTableDTO firstTime() {
		return reserveTableDAO.firstTime();
	}

	@Override
	public ReserveTableDTO secondTime() {
		return reserveTableDAO.secondTime();
	}

	@Override
	public ReserveTableDTO thirdTime() {
		return reserveTableDAO.thirdTime();
	}

	@Override
	public ReserveTableDTO fourthTime() {
		return reserveTableDAO.fourthTime();
	}

}
