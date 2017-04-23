package org.silkroad.abandon;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetYesterdayDate {

	public static void main(String[] args) {
		System.out.println(getYesterDayDate()); 
	}

	public static String getYesterDayDate() {
		String yesterdayStr = "";
		
		Date dateNow = new Date();
		Date dateYester = new Date();
		
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dateNow);                  // 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1);    // 设置为前一天
		dateYester = calendar.getTime();            //得到前一天的时间
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		yesterdayStr = sdf.format(dateYester);    //格式化前一天
		
		return yesterdayStr;
	}
}
