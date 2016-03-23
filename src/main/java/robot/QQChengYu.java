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
public class QQChengYu extends RecogniseRobot implements IAction{

	private String content;
	private String action;//所谓的行为就是发送一段文字
	private String keyWord;
	private Set<String> res;
	
	public QQChengYu() throws Exception {
		super();
		qiehuan();
		ClipboardOperate.setClipboardText("");
//		watchKeyWord();
	}


	@Override
	public void watch()  throws Exception{
//		content=getMessage(sX, sY, eX, eY)
		//获取屏幕信息
		content=getContent(10, 480, 1128, 591);
		content=content.trim();
		System.out.println(content);
	}

	@Override
	public void think()  throws Exception{
		// TODO Auto-generated method stub
		//有这么几种情况：
		//1.当前成语2.成语重复3.成语不存在
		//对应的行为为：查，查不重复的，换一个
		
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
		}else {
			throw new BasicException("不正常状态");
		}
	}

	@Override
	public void action()  throws Exception{
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
			throw new BasicException("action不应为空");
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
			Thread.sleep(300000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void work() throws Exception{
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
			try {
				cy.work();
				Thread.sleep(6000);
			} catch (Exception e) {
				e.printStackTrace();
				cy.waitAndTryAgain();
			}
		}
	}
}
