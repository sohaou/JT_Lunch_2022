package egovframework.jtLunch.admin.owner.Service;

import java.util.List;

import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.main.qrcode.DTO.QrCodeDTO;

public interface CheckAteUserService {
	//캘린더(한달)동안의 식사자 수 출력
	public int countAteUser(DateData dateData) throws Exception;
	//캘린더(한달)동안의 식사자 출력
	public List<QrCodeDTO> ateuser_list(DateData dateData) throws Exception;
	//선택한 날짜의 식사자 출력
	public List<QrCodeDTO> DayAteUserAll(String ate_date) throws Exception;
}
