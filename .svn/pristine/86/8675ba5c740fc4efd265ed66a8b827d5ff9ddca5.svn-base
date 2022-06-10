package egovframework.jtLunch.admin.owner.Service.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.jtLunch.admin.owner.DAO.CheckAteUserDAO;
import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.admin.owner.Service.CheckAteUserService;
import egovframework.jtLunch.main.qrcode.DTO.QrCodeDTO;

@Service("CheckAteUserService")
public class CheckAteUserServiceImpl implements CheckAteUserService{
	@Inject
	private CheckAteUserDAO dao;
	
	//캘린더(한달)동안의 식사자 수 출력
	@Override
	public int countAteUser(DateData dateData) throws Exception{
		return dao.countAteUser(dateData);
	}
	
	//캘린더(한달)동안의 식사자 출력
	@Override
	public List<QrCodeDTO> ateuser_list(DateData dateData) throws Exception{
		return dao.ateuser_list(dateData);
	}
	
	//선택한 날짜의 식사자 출력
	@Override
	public List<QrCodeDTO> DayAteUserAll(String ate_date) throws Exception{
		return dao.DayAteUserAll(ate_date);
	}
}
