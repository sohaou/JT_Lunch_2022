package egovframework.jtLunch.admin.dashboard.Util;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component("weekDayUtil")
public class WeekDayUtil {
	public static int subWeekNumberIsFirstDayAfterThursday(int year, int month, int day)  {
		
	    Calendar calendar = Calendar.getInstance(Locale.KOREA);
	    calendar.set(year, month - 1, day);
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    calendar.setFirstDayOfWeek(Calendar.MONDAY);

	    int weekOfDay = calendar.get(Calendar.DAY_OF_WEEK);

	    if ((weekOfDay >= Calendar.MONDAY) && (weekOfDay <= Calendar.THURSDAY)) {
	      return 0;
	    } else if (day == 1 && (weekOfDay < Calendar.MONDAY || weekOfDay > Calendar.TUESDAY))  {
	      return -1;
	    } else {
	      return 1;
	    }
	  }

	  public static int addMonthIsLastDayBeforeThursday(int year, int month, int day) {
		  
	    Calendar calendar = Calendar.getInstance(Locale.KOREA);
	    calendar.setFirstDayOfWeek(Calendar.MONDAY);
	    calendar.set(year, month - 1, day);

	    int currentWeekNumber = calendar.get(Calendar.WEEK_OF_MONTH);
	    int maximumWeekNumber = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);

	    if (currentWeekNumber == maximumWeekNumber) {
	      calendar.set(year, month - 1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	      int maximumDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

	      if (maximumDayOfWeek < Calendar.THURSDAY && maximumDayOfWeek > Calendar.SUNDAY) {
	        return 1;
	      } else {
	        return 0;
	      }
	      
	    } else {
	      return 0;
	    }
	  }

	  public static String getCurrentWeekOfMonth(int year, int month, int day)  {
		  
	    int subtractFirstWeekNumber = subWeekNumberIsFirstDayAfterThursday(year, month, day);
	    int subtractLastWeekNumber = addMonthIsLastDayBeforeThursday(year, month, day);

	    if (subtractLastWeekNumber > 0) {
	      day = 1;
	      subtractFirstWeekNumber = 0;
	    }

	    if (subtractFirstWeekNumber < 0)  {
	      Calendar calendar = Calendar.getInstance(Locale.KOREA);
	      calendar.set(year, month - 1, day);
	      calendar.add(Calendar.DATE, -1);

	      return getCurrentWeekOfMonth(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.DATE));
	    }

	    Calendar calendar = Calendar.getInstance(Locale.KOREA);
	    calendar.setFirstDayOfWeek(Calendar.MONDAY);
	    calendar.setMinimalDaysInFirstWeek(1);
	    calendar.set(year, month - (1 - subtractLastWeekNumber), day);

	    int dayOfWeekForFirstDayOfMonth = calendar.get(Calendar.WEEK_OF_MONTH) - subtractFirstWeekNumber;

	    return (calendar.get(Calendar.MONTH) + 1) + "월 " + dayOfWeekForFirstDayOfMonth + "주차";
	  }

}
