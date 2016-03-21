package robot;

import java.awt.AWTException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import exception.BasicException;

/**
 * 唯一完全正面增值的游戏
 * @author Administrator
 *
 */
public class QQChengYu extends TextRobot implements IAction{

	private String content;
	private String action;//所谓的行为就是发送一段文字
	private String keyWord;
	private Set<String> res;
	
	public QQChengYu() throws Exception {
		super();
		qiehuan();
//		watchKeyWord();
	}


	@Override
	public void watch() {
//		content=getMessage(sX, sY, eX, eY)
		//获取屏幕信息
		try {
			content=getMessage(404, 427, 743, 494);
			content=content.trim();
			System.out.println(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void think() {
		// TODO Auto-generated method stub
		//有这么几种情况：
		//1.当前成语2.成语重复3.成语不存在
		//对应的行为为：查，查不重复的，换一个
		try{
			if(content.contains("成语接龙游戏开始")){
				keyWord=content.substring(content.length()-1, content.length());
				System.out.println(keyWord);
				process();
			}else if(content.contains("成语接龙游戏超时自动结束")){
				throw new RuntimeException("游戏结束");
			}else if(content.contains("请重新输入")){
				Iterator<String> it=res.iterator();
				if(it.hasNext()){
					action=it.next();
					res.remove(action);
				}else{
					throw new RuntimeException("成语set中没有值了");
				}
			}else if(content.contains("接龙成功")){
				keyWord=content.substring(content.length()-2, content.length());
				process();
			}else {
				throw new RuntimeException("不正常状态");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		//输出
		if(action!=null){
			inputText(action);
			try {
				qqSend();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			throw new RuntimeException("action不应为空");
		}
	}
	private void process(){
		if(keyWord!=null&&keyWord.length()==1){
			try {
				res=Chengyu.getFirstList(keyWord);
			} catch (BasicException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Iterator<String> it=res.iterator();
			if(it.hasNext()){
				action=it.next();
				res.remove(action);
			}else{
				throw new RuntimeException("成语set中没有值了");
			}
		}else{
			throw new RuntimeException("keyWord格式不正确");
		}
	}
	public void work(){
		watch();
		think();
		action();
	}
	public static void main(String[] args) throws Exception {
		QQChengYu cy=new QQChengYu();
//		cy.think();
//		cy.action();
//		Thread.sleep(6000);
		while(true){
			cy.work();
			Thread.sleep(6000);
		}
	}
}
