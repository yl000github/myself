package robot;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class Demo {
	  private Robot robot = null;
      private Dimension dim;
      private double width;
      private double height;
	    public Demo()
	    {
	        try
	        {
	            robot = new Robot();
	            dim=Toolkit.getDefaultToolkit().getScreenSize();
	            width=dim.getWidth();
	            height=dim.getHeight();
	        }
	        catch (AWTException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	      
	    //模拟Ctrl+Alt+Z的按下和抬起
	    public void keyBoardDemo()
	    {
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ALT);
	        robot.keyPress(KeyEvent.VK_Z);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_ALT);
	        robot.keyRelease(KeyEvent.VK_Z);
	    }
	      
	    public static void main(String[] args)
	    {
	        Demo demo = new Demo();
	        demo.keyBoardDemo();
	    }
}
