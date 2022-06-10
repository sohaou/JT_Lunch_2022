package egovframework.jtLunch.admin.owner.Service;

import java.util.List;
import java.util.Map;

import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.main.qrcode.DTO.QrCodeDTO;

public interface CheckAteUserService {
	// 금일 식사자 수 count
	public int countTodayEat(String eatDate, String id) throws Exception;
	// 캘린더(한달)동안의 식사자 수 출력
	public int countAteUser(DateData dateData) throws Exception;
	// 캘린더(한달)동안의 식사자 출력
	public List<QrCodeDTO> ateuser_list(DateData dateData) throws Exception;
	// 선택한 날짜의 식사자 출력
	public List<QrCodeDTO> DayAteUserAll(String ate_date) throws Exception;
	// 선택한 날짜의 식사자수 출력
	public int CountAteUserDate(String ate_date) throws Exception;
	//
	public List<Map<String,String>> AteUserInMonth(DateData dateData) throws Exception;
}
