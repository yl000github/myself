package robot1;

import utils.DateUtil;
public abstract class QQMan extends AMan{

	public QQMan() throws Exception {
		super();
		qiehuan();
		clearClipboard();
		String date=DateUtil.getNowFormat("yyyy/M/dd");
		System.out.println(date);
		for (int i = 0; i < users.length; i++) {
			users[i]+=" "+date;
		}
	}
	public String getMessageMove(int sX,int sY,int eX,int eY) throws Exception{

		String stored=getClipboard();
		int width=eX-sX;
		int height=eY-sY;
		int tryCount=5;
		int changeW=width/3;
		int changeH=height/3;
		int deltW=changeW/tryCount/2;
		int deltH=changeH/tryCount/2;
		//首先尝试缩小范围
		for (int i = 0; i < tryCount; i++) {
			String msg=getMessage(sX+deltW*i, sY+deltH*i, eX-deltW*i, eY-deltH*i);
			if(!msg.equals(stored)){
				return msg;
			}
		}
		//然后尝试扩大范围，适当缩小扩大的范围值，以免点到其他地方，产生未知错误
		for (int i = 0; i < tryCount/2; i++) {
			String msg=getMessage(sX-deltW*i, sY-deltH*i, eX+deltW*i, eY+deltH*i);
			if(!msg.equals(stored)){
				return msg;
			}
		}
		throw new RuntimeException("无法获取剪切板的内容");
	
	}
	String []users={"杨林","vip机器人"};
	public String getMessageCtrl(int sX,int sY,int eX,int eY) throws Exception{
		//获取最新的消息
		int mX=(sX+eX)/2;
		int mY=(sY+eY)/2;
		moveAndClick(mX, mY);
		ctrlA();Thread.sleep(100);
		copy();Thread.sleep(100);
		String msg=getClipboard();
		//分析的思路，根据角色名做区分，找出最后一条消息
		int max=-1;
		for (int i = 0; i < users.length; i++) {
			int t=msg.lastIndexOf(users[i]);
			if(t>max) max=t;
		}
		if(max==-1) throw new Exception("找不到对白信息");
		String result=msg.substring(max, msg.length());
		return result;
	}
	@Override
	public void watch() throws Exception {
		String pre=content;
		boolean f=true;
		//退出循环的条件是，包含机器人、和之前的内容不同 !content.contains(users[1])||
		while(pre.equals(content)){
			content=getMessageCtrl(0, 92, 1142, 592);
			content=content.trim();
			//添加强控
			if(content.contains("stop")){
				System.exit(1);
			}
			System.out.println("观察到的内容为："+content);
			//首次不用等
			if(!f){
				pause();
			}
			f=false;
		}
	}
	@Override
	protected void pause() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
