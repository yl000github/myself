package robot;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MyRobot extends Robot {
	String s3 = "";
	private Dimension dim;
    public double width;
    public double height;
	public MyRobot() throws AWTException {
		super();
		dim=Toolkit.getDefaultToolkit().getScreenSize();
        width=dim.getWidth();
        height=dim.getHeight();
        System.out.println(width+"*"+height);
	}

	public void YiDong(int a, int b) {
		this.mouseMove(a, b);
	}

	public void ZanTing(int time) {
		try {
			new Thread().sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void DianJi() {
		this.mousePress(MouseEvent.BUTTON1_MASK);
		this.mouseRelease(MouseEvent.BUTTON1_MASK);
	}

	public void HuiChe() {
		this.keyPress(KeyEvent.VK_ENTER);
		this.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	
	public static void main(String[] args) throws AWTException {
		MyRobot robot=new MyRobot();
		robot.mouseMove(0, (int)robot.height);
		robot.DianJi();
		System.out.println("hello");
//		robot. 
	}
}
