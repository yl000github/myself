package robot;

import java.awt.AWTException;

/**
 * 唯一完全正面增值的游戏
 * @author Administrator
 *
 */
public class QQChengYu extends TextRobot implements IAction{

	private String content;
	private String action;//所谓的行为就是发送一段文字
	private String keyWord;
	
	public QQChengYu() throws Exception {
		super();
		qiehuan();
		watchKeyWord();
	}

	private void watchKeyWord() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void watch() {
//		content=getMessage(sX, sY, eX, eY)
		//获取屏幕信息
	}

	@Override
	public void think() {
		// TODO Auto-generated method stub
		//有这么几种情况：
		//1.当前成语2.成语重复3.成语不存在
		//对应的行为为：查，查不重复的，换一个
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		//输出
	}

}
