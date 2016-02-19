package robot;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextRobot extends Robot{
	private Dimension dim;
    public double width;
    public double height;
	public TextRobot() throws AWTException {
		super();
		dim=Toolkit.getDefaultToolkit().getScreenSize();
        width=dim.getWidth();
        height=dim.getHeight();
        System.out.println(width+"*"+height);
	}
	public void inputText(String src){
		
	}
	public void mouseClick(){
		this.mousePress(MouseEvent.BUTTON1_MASK);
		this.mouseRelease(MouseEvent.BUTTON1_MASK);
	}
	public void keyClick(int c){
		this.keyPress(c);
		this.keyRelease(c);
	}
	public void enter(){
		this.keyPress(KeyEvent.VK_ENTER);
		this.keyRelease(KeyEvent.VK_ENTER);
	}
	public boolean isIn(int goal,int min,int max){
		return goal>=min&&goal<=max;
	}
	/**
	 * 有效范围为：0-9,a-z,A-Z
	 * @param c
	 */
	public void inputChar(char c){
		if(isIn(c,48,57)){
			//0-9
			keyClick(c);
		}else if(isIn(c,65,90)){
			//A-Z
			this.keyPress(KeyEvent.VK_SHIFT);
			this.keyPress(c);
			this.keyRelease(KeyEvent.VK_SHIFT);
			this.keyRelease(c);
		}else if(isIn(c,97,122)){
			keyClick(c-32);
		}else if(isIn(c,0x4E00,0x9FA5)){
			//基本汉字,获取unicode，必须配合unicode输入法使用
			String s=Integer.toHexString(c);
			System.out.println(s);
			for (int i = 0; i < s.length(); i++) {
				char sc=s.charAt(i);
				inputChar(sc);
			}
//			keyClick(KeyEvent.VK_SPACE);
		}else{
			//not support
			
		}
	}
	public void move(int x,int y){
		this.mouseMove(x, y);
	}
	public void openQQ(){
		keyPress(KeyEvent.VK_CONTROL);
		keyPress(KeyEvent.VK_ALT);
		keyPress(KeyEvent.VK_Z);
		keyRelease(KeyEvent.VK_CONTROL);
		keyRelease(KeyEvent.VK_ALT);
		keyRelease(KeyEvent.VK_Z);
	}
	public void screenShot() throws IOException{
		BufferedImage image=createScreenCapture(new Rectangle(0, 0, (int)width, (int)height));
		ImageIO.write(image,"png",new File("f:/s.png"));
	}
	public static void main(String[] args) throws AWTException, InterruptedException {
		
	}
}
