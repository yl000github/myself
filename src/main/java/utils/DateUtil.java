package utils;

import java.util.Date;

public class DateUtil {
	public static Date getNow(){
		return new Date();
	}
	//unit milliseconds 
	public static long diff(Date start,Date end){
		return end.getTime()-start.getTime();
	}
	public static String diffString(Date start,Date end){
		long l=diff(start, end);
//		long millisecond=l%1000;
		long second=l/1000%60;
		long minute=l/1000/60%60;
		long hour=l/1000/60/60%24;
		long day=l/1000/60/60/24;
		return day+"天 "+hour+":"+minute+":"+second;
	}
}
