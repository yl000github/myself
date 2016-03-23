package robot;

/**
 * 强化获取屏幕内容的成功率
 * 主要思路是多次尝试
 * @author Administrator
 *
 */
public class RecogniseRobot extends TextRobot{

	public RecogniseRobot() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	//通过不断尝试，知道能够取到内容为止
	public String getContent(int sX,int sY,int eX,int eY) throws Exception{
		String stored=ClipboardOperate.getClipboardText();
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
		//然后尝试扩大范围
		for (int i = 0; i < tryCount; i++) {
			String msg=getMessage(sX-deltW*i, sY-deltH*i, eX+deltW*i, eY+deltH*i);
			if(!msg.equals(stored)){
				return msg;
			}
		}
		throw new RuntimeException("无法获取剪切板的内容");
	}
}
