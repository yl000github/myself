package robot1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.DateUtil;
import utils.XlsUtil;

/**
 * 记录结果的能力
 * 基本的处理
 * @author Administrator
 *
 */

public abstract class QQSanGong extends QQMan{
	String action="";
	int everyInvestMoney=50;
	boolean isWin=true;
	int currentMoney;
	int totalMoney;
	String path;
	int tryNumber=1;
	public QQSanGong() throws Exception {
		super();
	}
	@Override
	public void think() throws Exception {
		if(content.contains("玩家赢")){
			isWin=true;currentMoney+=everyInvestMoney;
			writeToExcel();
			win();
		}else if(content.contains("玩家输")){
			isWin=false;currentMoney-=everyInvestMoney;
			writeToExcel();
			lose();
		}else if(content.contains("当前等级")){
			totalMoney=Integer.parseInt(content.substring(content.indexOf("积分：")+3,content.indexOf("当前等级")-1));
			currentMoney=totalMoney;
			ready();
		}else {
			//do nothing
//			action=""
		}
		if(everyInvestMoney<50){
			System.out.println("穷困潦倒啊，50都没有");
			System.exit(1);
		}
	}
	public abstract void win();
	public abstract void lose();
	public abstract void ready();

	@Override
	public void action() throws Exception {
		inputText("三公 "+everyInvestMoney);
		qqSend();
	}
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
		while(true){
			try {
				work();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
