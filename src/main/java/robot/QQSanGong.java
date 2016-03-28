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
	public String getContent(int sX, int sY, int eX, int eY) throws Exception {
		String stored=ClipboardOperate.getClipboardText();
		int tryCount=5;
		int delt=10;
		//首先尝试缩小范围
		for (int i = 0; i < tryCount; i++) {
			String msg=getMessage(sX+delt*i, sY+delt*i, eX-delt*i, eY-delt*i);
			if(!msg.equals(stored)){
				return msg;
			}
		}
		throw new RuntimeException("无法获取剪切板的内容");
	
	}

	@Override
	public void watch() throws Exception {
		content=getContent(10, 464, 1128, 591);
		content=content.trim();
		System.out.println(content);
	}

	@Override
	public void think() throws Exception {
		if(isFirstCircle) {
			isFirstCircle=false;
			return;
		}
		if(content.contains("玩家赢")){
			System.out.println("赢了"+everyInvestMoney);
			endTotalMoney+=everyInvestMoney;
			everyInvestMoney*=2;
		}else if(content.contains("玩家输")){
			endTotalMoney-=everyInvestMoney;
			System.out.println("输了"+everyInvestMoney);
			throw new BasicException("本轮gg");
		}else if(content.contains("当前等级")){
			clearClipboard();
			totalMoney=Integer.parseInt(content.substring(content.indexOf("积分：")+3,content.indexOf("当前等级")-1));
			endTotalMoney=totalMoney;
			investmentMoney=(int) (totalMoney*0.03);
			System.out.println("总金额："+totalMoney+"  投资额："+investmentMoney);
//			//要先开一局
//			everyInvestMoney=50;
		}else{
			throw new BasicException("非输非赢");
		}
		System.out.println(everyInvestMoney);
	}

	@Override
	public void action() throws Exception {
		if(everyInvestMoney>=50){
			inputText("三公 "+everyInvestMoney);
			qqSend();
		}else if(everyInvestMoney==-1){
		}else{
			System.out.println("资金不够，无法游戏");
		}
		Thread.sleep(6000);
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
	int everyInvestMoney=-1;//表示初始状态时不进行游戏;
	int endTotalMoney;
	//游戏的策略
	//准备阶段,确定好投资的限额
	public void ready() throws Exception{
//		initExcel();
		inputText("积分系统");qqSend();Thread.sleep(6000);
		work();
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
	boolean isFirstCircle=false;
	//投资阶段
	public void run(){
		if(strategy.equals("greedy")){
			//总共做n次尝试，每一次都是全压
			int i=tryCount;
			while(i-->0){
				everyInvestMoney=investmentMoney/tryCount;
				int t=(int)( everyInvestMoney* Math.pow(2, 3));
				isFirstCircle=true;
				try { 
					while(everyInvestMoney>50&&everyInvestMoney<t){
						work();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	//总结阶段
	public void clearing(){
		if(endTotalMoney>totalMoney){
			System.out.println("投资成功,资产对比："+totalMoney+"  "+endTotalMoney);
		}else{
			System.out.println("投资失败,资产对比："+totalMoney+"  "+endTotalMoney);
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
