package robot;

import java.awt.AWTException;
import java.io.File;

import org.junit.Test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class QQXiaoyun {
	@Test
	public void chengyu() throws Exception{
		String head="定";
		TextRobot robot=new TextRobot();
		robot.qiehuan(); 
		for (int i = 0; i < 15; i++) { 
			String cur=Chengyu.getFirst(head);
			robot.inputText(cur); 
			robot.qqSend();
			head=cur.substring(cur.length()-1,cur.length());
		}
	}
	@Test
	public void sangong()throws Exception{
		TextRobot robot=new TextRobot();
		robot.qiehuan(); 
		for (int i = 0; i < 100; i++) { 
			robot.paste(); 
			robot.qqSend(); 
		}
		
	}
	@Test
	public void sangongRan()throws Exception{
		TextRobot robot=new TextRobot();
		robot.qiehuan(); 
		int min=50,max=500;
		String pre="三公 ";
		for (int i = 0; i < 100; i++) { 
			int n=(int) (min+Math.random()*(max-min));
			robot.inputText(pre+n);
			robot.qqSend(); 
			Thread.sleep(300);
		}
		
	}
	@Test
	public void caiquanRan()throws Exception{
		TextRobot robot=new TextRobot();
		robot.qiehuan(); 
		int min=50,max=500;
		String pre="猜拳 ";
		String[] s={"石头 ","剪刀 ","布 "};
		for (int i = 0; i < 50; i++) { 
			int n=(int) (min+Math.random()*(max-min));
			int nn=(int) (Math.random()*3);
			robot.inputText(pre+s[nn]+n);
			robot.qqSend(); 
			Thread.sleep(300);
		}
		
	}
	@Test
	public void hello()throws Exception{
		TextRobot r=new TextRobot();
//		r.mouseFromTo(0, 0, 1366, 768);
		r.qiehuan();
		Thread.sleep(1000);
		String c=r.getMessage(399, 193, 774, 484);
		System.out.println(c);
//		r.mouseMove(774, 484);
	}
	/**
	 * @throws Exception
	 */
	@Test
	public void text() throws Exception{
		String pre="我";
		TextRobot robot=new TextRobot();
		robot.qiehuan(); 
		int []l=new int[]{
				1,2,3,4,5,6,7,8,9,10,10,9,8,7,6,5,4,3,2,1
		};
		for (int i = 0; i < 30; i++) { 
			int len=l[i%l.length];
			StringBuffer sb=new StringBuffer();
			for (int j = 0; j < len; j++) {
				sb.append("日");
			}
			robot.inputText(pre+sb.toString()); 
			robot.qqSend();
		}
	}
	@Test
	public void screenShot() throws Exception{
		TextRobot robot=new TextRobot();
		robot.qiehuan(); 
		robot.screenShot(401, 205, 362, 294);
        File imageFile = new File("f:/qq.png");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setDatapath("C:\\Users\\Yang\\Downloads\\-Tess4J-3.0-src\\Tess4J\\tessdata");
        instance.setLanguage("chi_sim");
        String result = instance.doOCR(imageFile);
        System.out.println(result);
         
	}
}
