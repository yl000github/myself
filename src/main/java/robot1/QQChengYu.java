package robot1;

import java.util.Iterator;
import java.util.Set;

import exception.BasicException;
import robot.Chengyu;

public class QQChengYu extends QQMan{
	private String action;//所谓的行为就是发送一段文字
	private String keyWord;
	private Set<String> res;
	
	public QQChengYu() throws Exception {
		super();
	}
	@Override
	public void think() throws Exception {
		System.out.println("hi");
		if(content==null) throw new Exception("未能获得content");
		if(content.contains("成语接龙游戏开始")){
			keyWord=content.substring(content.length()-1, content.length());
			System.out.println(keyWord);
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
			System.out.println(keyWord);
			process();
		}else if(content.contains("玩游戏就要专心玩")){
			int dqcy=content.indexOf("当前成语");
			keyWord=content.substring(dqcy+8, dqcy+9);
			System.out.println(keyWord);
			process();
		}else if(content.contains("需要休息120分钟")){
			System.out.println("无法游戏了");
			System.exit(1);
		}else{
			//尚未开始游戏
			action="成语接龙";
//			throw new BasicException("不正常状态");
		}
	}

	@Override
	public void action() throws Exception {
		//输出
		if(action!=null){
			inputText(action);
			qqSend();
		}else{
			throw new Exception("action不应为空");
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
		while(true){
			try {
				cy.work();
			} catch (Exception e) {
				e.printStackTrace();
				cy.waitAndTryAgain();
			}
		}
	}
}
