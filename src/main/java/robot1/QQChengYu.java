package robot1;

import java.util.Iterator;
import java.util.Set;

import exception.BasicException;
import exception.InfoException;
import robot.Chengyu;
import swing.resolve.ISwitch;

public class QQChengYu extends QQMan {
	private String keyWord;
	private Set<String> res;
	
	public QQChengYu() throws Exception {
		super();
	}
	@Override
	public void think() throws Exception {
		System.out.println("think");
		if(content==null) throw new Exception("未能获得content");
		if(content.contains(users[0])){
			//自己输入的信息，其实最好也要做一个判断的工作
			if(content.contains("start")){
				action="成语接龙";
				return;
			}else{
				throw new InfoException("自己的未知信息");
			}
		}
		if(content.contains("成语接龙游戏开始")){
			keyWord=content.substring(content.length()-1, content.length());
			info("关键词："+keyWord);
			process();
		}else if(content.contains("成语接龙游戏超时自动结束")){
			throw new BasicException("游戏结束");
		}else if(content.contains("请重新输入")||content.contains("无法继续进行游戏！请更换成语")){
			Iterator<String> it=res.iterator();
			if(it.hasNext()){
				action=it.next();
				res.remove(action);
			}else{
				throw new BasicException("成语set中没有值了");
			}
		}else if(content.contains("接龙成功")){
			keyWord=content.substring(content.length()-1, content.length());
			info("关键词："+keyWord);
			process();
		}else if(content.contains("玩游戏就要专心玩")){
			int dqcy=content.indexOf("当前成语");
			keyWord=content.substring(dqcy+8, dqcy+9);
			info("关键词："+keyWord);
			process();
		}else if(content.contains("需要休息120分钟")){
			info("无法游戏了");
			System.exit(1);
		}else{
			//尚未开始游戏
//			action="成语接龙";
			throw new InfoException("成语接龙不正常状态");
		}
	}

	private void process() throws Exception{
		if(keyWord!=null&&keyWord.length()==1){
			res=Chengyu.getFirstList(keyWord);
			Iterator<String> it=res.iterator();
			if(it.hasNext()){
				action=it.next();
				res.remove(action);
			}else{
				throw new BasicException("成语set中没有值了");
			}
		}else{
			throw new BasicException("keyWord格式不正确");
		}
	}
	public void waitAndTryAgain(){
		try {
			inputText("结束成语接龙");
			qqSend();
			Thread.sleep(6000);
			inputText("成语接龙");
			qqSend();
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		QQChengYu cy=new QQChengYu();
		cy.start();
	}
	@Override
	public void threadRunning() {
		try {
			work(); 
		} catch (Exception e) {
			e.printStackTrace();
			waitAndTryAgain();
		}
	}
	
}
