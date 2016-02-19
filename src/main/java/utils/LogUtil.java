package utils;
/**
 * 模仿log4j 做一个简单的日志系统
 * @author Yang
 *
 */
public class LogUtil {
	public static final String DIR="F:/logs/";
	public static void logDaily(String msg){
		String now=DateUtil.getNowFormat("yy-MM-dd");
		String filename="log-"+now+".log";
		FileUtil.writeAdd(DIR+filename, msg);
	}
}
