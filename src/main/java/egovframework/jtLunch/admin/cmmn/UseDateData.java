package egovframework.jtLunch.admin.cmmn;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// 날짜 관련된 정보를 다루는 클래스
public class UseDateData {
	// 날짜 형식
	DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat con = new SimpleDateFormat("MM/dd");
	
	Calendar cal = Calendar.getInstance();
	
	// 랜덤 생성 식단에 들어갈 날짜 반환
	public String randomDietDate() {
		Calendar cal1 = Calendar.getInstance();
		// 요일
		DateFormat dtf1 = new SimpleDateFormat("E");
	    // 오늘 요일
		String day = dtf1.format(cal.getTime());
		
		// 금요일이면 월요일 식단 생성을 위해 +3
	    if(day.equals("금")) {
	    	cal1.add(Calendar.DATE, 3);
	    } else {
	    	cal1.add(Calendar.DATE, 1);
	    }
	    
	    String dietDate = dtf.format(cal1.getTime());
	    
	    return dietDate;
	}
	
	// 오늘 날짜 반환
	public String getToday() {
        String strToday = dtf.format(cal.getTime());
        
        return strToday;
	}
	
	// 현재 시간 반환
	public String getNow() {
		String strnow = now.format(cal.getTime());
		
		return strnow;
	}
	
	// 어제 날짜 반환
	public String getYesterday() {
		// 요일
		DateFormat dtf1 = new SimpleDateFormat("E");
	    // 오늘 요일
		String day = dtf1.format(cal.getTime());
		
		// 월요일이면 금요일 식사자 수 확인을 위해 -3
	    if(day.equals("월")) {
	    	cal.add(Calendar.DATE, -3);
	    } else {
	    	cal.add(Calendar.DATE, -1);
	    }
	    
	    String eatDate = dtf.format(cal.getTime());
	    
	    return eatDate;
	}
	
	// 날짜 계산 _ 오늘날짜를 기준으로만 계산 가능
	public String getCalcDate(String type, int data) {
		Calendar cal2 = Calendar.getInstance();
		
		if(type == "d") {
			// 일 계산일 경우
			cal2.add(Calendar.DATE, data);
		} else if(type == "m") {
			// 월 계산일 경우
			cal2.add(Calendar.MONTH, data);
		} else if(type == "y") {
			// 년 계산일 경우
			cal2.add(Calendar.YEAR, data);
		}
		String resultDate = dtf.format(cal2.getTime());
		
		return resultDate;
	}
	// 날짜 형식 변환 _ yyyy-mm-dd 에서 yy/mm/dd로
	public String convertDate(String date) throws ParseException {
		Date conDate = dtf.parse(date);
		String result = con.format(conDate);
		
		return result;
	}
}
