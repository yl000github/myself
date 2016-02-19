package basic;

import java.awt.AWTException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import robot.TextRobot;

public class RobotTest {
	TextRobot r;
	@Before
	public void b() throws AWTException{
		r=new TextRobot();
	}
	
	@Test 
	public void text1() throws AWTException, InterruptedException{
		System.out.println("begin");
		TextRobot robot=new TextRobot();
		robot.mouseMove(200, (int)robot.height);
		robot.mouseClick();
		Thread.sleep(500);//操作系统取指令有个等待时间
		robot.mouseMove((int)robot.width/2, (int)robot.height/2);
		robot.mouseClick();
		String src="你真是聪明";
		for (int i = 0; i < src.length(); i++) {
			robot.inputChar(src.charAt(i)); 
		}
//		robot.inputChar('1'); 
//		robot.inputChar('A'); 
//		robot.inputChar('a'); 
		System.out.println("end");
	}
	@Test
	public void screen() throws IOException{
		r.screenShot();
	}
}
