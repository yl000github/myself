package swing.operation;

import java.util.Date;

import robot1.Action;

public class WS extends Action{
	static String[] strs={
			"The lunatic, the lover and the poet are of imagination all compact.",
			"上邪,我欲与君相知,长命无绝衰.山无陵,江水为竭,冬雷震震,夏雨雪,天地合,乃敢与君绝!",
			"美貌比金银更容易引起歹心.",
			"To be or not to be: that is a question.",
			"Cowards die many times before their deaths; the valiant never taste of death but once. "
	};

	public WS() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws Exception {
		WS w=new WS();
		Thread.sleep(7000);
//		w.qiehuan();
		int index=0;int startHour=1;
//		while(index<strs.length){
//			Date now=new Date();
//			if(now.getHours()==startHour){
//				int min=(int) (Math.random()*60);
//				Thread.sleep(min*60000);
//				w.inputText(strs[index]);
//				w.qqSend();
//				index++;
//				startHour++;
//			}
//			Thread.sleep(60000);
//		}
		while(index<strs.length){
			Date now=new Date();
//			int min=(int) (Math.random()*60);
//			Thread.sleep(min*60000);
			w.inputText(strs[index]);
			w.qqSend();
			index++;
			startHour++;
			Thread.sleep(5000);
		}
	}
}
