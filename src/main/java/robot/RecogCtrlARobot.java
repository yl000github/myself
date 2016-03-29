package robot;

import utils.DateUtil;

public class RecogCtrlARobot extends TextRobot{

	public RecogCtrlARobot() throws Exception {
		super();
		String date=DateUtil.getNowFormat("yyyy/M/dd");
		System.out.println(date);
		for (int i = 0; i < users.length; i++) {
			users[i]+=" "+date;
		}
	}
	String []users={"杨林","vip机器人"};
	@Override
	public String getMessage(int sX, int sY, int eX, int eY) throws Exception {
		//获取最新的消息
		int mX=(sX+eX)/2;
		int mY=(sY+eY)/2;
		moveAndClick(mX, mY);
		ctrlA();
		copy();
		String msg=ClipboardOperate.getClipboardText();
		//分析的思路，根据角色名做区分，找出最后一条消息
		int max=-1;
		for (int i = 0; i < users.length; i++) {
			int t=msg.lastIndexOf(users[i]);
			if(t>max) max=t;
		}
		String result=msg.substring(max, msg.length());
		return result;
	}
	public static void main(String[] args) throws Exception {
		RecogCtrlARobot r=new RecogCtrlARobot();
		r.qiehuan();
		String msg=r.getMessage(0, 92, 1142, 592);
		System.out.println(msg);
	}
}
