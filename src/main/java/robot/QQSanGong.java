package robot;

import java.util.ArrayList;
import java.util.List;

import exception.BasicException;

public class QQSanGong extends RecogniseRobot implements IAction{
	
	private String content;
	public QQSanGong() throws Exception {
		super();
		qiehuan();
		ClipboardOperate.setClipboardText("");
	}

	@Override
	public void watch() throws Exception {
		content=getContent(10, 480, 1128, 591);
		content=content.trim();
		System.out.println(content);
	}

	@Override
	public void think() throws Exception {
		if(content.contains("赢")){
			everyInvestMoney*=2;
		}else if(content.contains("输")){
			throw new BasicException("本轮gg");
		}else{
			throw new BasicException("非输非赢");
		}
	}

	@Override
	public void action() throws Exception {
		if(everyInvestMoney>=50){
			inputText("三公 "+everyInvestMoney);
			qqSend();
		}else{
			System.out.println("资金不够，无法游戏");
		}
	}
	//基本工作机制
	public void work() throws Exception{
		watch();
		think();
		action();
	}
	int totalMoney;
	int investmentMoney;
	int storedMoney;
	String strategy="greedy";
	int tryCount=10;
	int everyInvestMoney;
	int endTotalMoney;
	//游戏的策略
	//准备阶段,确定好投资的限额
	public void ready() throws Exception{
		initExcel();
		inputText("积分系统");qqSend();Thread.sleep(6000);
		watch();
		clearClipboard();
		//TODO
		totalMoney=Integer.parseInt(content.substring(1));
		investmentMoney=(int) (totalMoney*0.3);
		strategy="greedy";
	}	
	private void initExcel() {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<>();
		list.add("轮数\\结果");
		for (int i = 0; i < 15; i++) {
			list.add(String.valueOf(i));
		}
		for (int i = 0; i < tryCount; i++) {
			
		}
	}

	//投资阶段
	public void run(){
		if(strategy.equals("greedy")){
			//总共做n次尝试，每一次都是全压
			int i=tryCount;
			while(i-->0){
				everyInvestMoney=investmentMoney/tryCount;
				try {
					while(everyInvestMoney>50&&everyInvestMoney<10000){
						work();
					}
				} catch (Exception e) {
					e.printStackTrace();
					i++;
				}
			}
		}
	}
	//总结阶段
	public void clearing(){
		if(endTotalMoney>totalMoney){
			System.out.println("投资成功,资产对比："+totalMoney+"  "+"endTotalMoney");
		}else{
			System.out.println("投资失败,资产对比："+totalMoney+"  "+"endTotalMoney");
		}
	}
	public void playing() throws Exception{
		ready();
		run();
		clearing();
	}
	//输出测试结果报表
	List<List<String>> results=new ArrayList<>();
	public static void main(String[] args) {
		try {
			QQSanGong sg=new QQSanGong();
			sg.playing();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
