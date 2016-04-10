package robot1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exception.InfoException;
import robot1.dao.QQSanGongDao;
import swing.resolve.MsgQueue;
import utils.DateUtil;
import utils.XlsUtil;

/**
 * 记录结果的能力
 * 基本的处理
 * @author Administrator
 *
 */

public abstract class QQSanGong extends QQMan{
	
	int everyInvestMoney=50;
	boolean isWin=true;
	int currentMoney;
	int totalMoney;
	String path;
	int tryNumber=1;
	public QQSanGong() throws Exception {
		super();
		excelInit();
	}
	@Override
	public void think() throws Exception {
//		MsgQueue.addMsg("三公 think");
		if(content.contains(users[0])){
			//自己输入的信息，其实最好也要做一个判断的工作
			if(content.contains("start")){
				action="积分系统";
			}else{
				throw new InfoException("自己的未知信息");
//				action="自己的未知信息";
			}
			return;
//			throw new Exception("非机器人的回答");
		}
		if(content.contains("闲家赢")){
			info("玩家赢");
			isWin=true;currentMoney+=everyInvestMoney;
			writeToExcel();
			writeToDB();
			win();
			action="三公 "+everyInvestMoney;
		}else if(content.contains("闲家输")){
			info("玩家输");
			isWin=false;currentMoney-=everyInvestMoney;
			writeToExcel();
			writeToDB();
			lose();
			action="三公 "+everyInvestMoney;
		}else if(content.contains("当前等级")){
			totalMoney=Integer.parseInt(content.substring(content.indexOf("积分：")+3,content.indexOf("存款")-1));
			currentMoney=totalMoney;
			ready();
			action="三公 "+everyInvestMoney;
		}else if(content.contains("请明日再来")){
			System.exit(1);
		}else {
			//do nothing
			throw new InfoException("三公不正常情况");
//			action="三公不正常情况";
		}
		if(everyInvestMoney<50){
			info("穷困潦倒啊，50都没有");
			System.exit(1);
		}
	}
	QQSanGongDao dao=new QQSanGongDao();
	private void writeToDB() {
		String rs=isWin?"1":"0";
		try { 
			dao.insert(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public abstract void win();
	public abstract void lose();
	/**
	 * 游戏开始前的数据记录
	 */
	public abstract void ready();

	public void excelInit(){
		List<String> l=new ArrayList<>();
		l.add("轮数");
		l.add("投资额");
		l.add("胜负结果");
		l.add("总金额");
		String fileName="sg-"+DateUtil.getNowFormatD()+".xlsx";
		path="D:/excel/"+fileName;
		XlsUtil.write(path, l, null);
	}
	public void writeToExcel(List<String> l) throws IOException{
		List<List<String>> list=new ArrayList<>();
		list.add(l);
		XlsUtil.writeAdd(path, list);
	}
	public void writeToExcel() throws IOException{
		List<String> l=new ArrayList<>();
		l.add(String.valueOf(tryNumber++));
		l.add(String.valueOf(everyInvestMoney));
		l.add(String.valueOf(isWin));
		l.add(String.valueOf(currentMoney));
		List<List<String>> list=new ArrayList<>();
		list.add(l);
		XlsUtil.writeAdd(path, list);
	}
	public void play(){
		try {
			work();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
