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
	public TextRobot() throws Exception {
		super();
		dim=Toolkit.getDefaultToolkit().getScreenSize();
        width=dim.getWidth();
        height=dim.getHeight();
        System.out.println(width+"*"+height);
	}
	/**
	 * 输入中文句子,配合unicode输入法
	 * @param src
	 */
	public void inputText(String src){
		for (int i = 0; i < src.length(); i++) {
			char c=src.charAt(i);
			if(c>='0'&&c<='9'||c>='a'&&c<='z'||c>='A'&&c<='Z'){
				keyClick(KeyEvent.VK_CAPS_LOCK);
				inputChar(c);
				keyClick(KeyEvent.VK_CAPS_LOCK);
			}else{
				inputChar(c);
			}
		}
	}
	private void pause(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void mouseLeftClick(){
		this.mousePress(MouseEvent.BUTTON1_MASK);
		this.mouseRelease(MouseEvent.BUTTON1_MASK);
		pause();
	}
	public void mouseRightClick(){
		this.mousePress(MouseEvent.BUTTON2_MASK);
		this.mouseRelease(MouseEvent.BUTTON2_MASK);
		pause();
	}
	public void keyClick(int c){
		this.keyPress(c);
		this.keyRelease(c);
//		pause();
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
		}else if(c==32){
			keyClick(KeyEvent.VK_SPACE);
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
	public void screenShot(int sX,int sY,int width,int height) throws IOException{
		BufferedImage image=createScreenCapture(new Rectangle(sX, sY, width, height));
		ImageIO.write(image,"png",new File("f:/qq.png"));
	}
	public void copy(){
		keyPress(KeyEvent.VK_CONTROL);
		keyPress(KeyEvent.VK_C);
		keyRelease(KeyEvent.VK_CONTROL);
		keyRelease(KeyEvent.VK_C);
	}
	public void paste(){
		keyPress(KeyEvent.VK_CONTROL);
		keyPress(KeyEvent.VK_V);
		keyRelease(KeyEvent.VK_CONTROL);
		keyRelease(KeyEvent.VK_V);
	}
	public void qiehuan() throws InterruptedException{
		keyPress(KeyEvent.VK_ALT);
		keyPress(KeyEvent.VK_TAB);
		Thread.sleep(100);
		keyRelease(KeyEvent.VK_ALT);
		keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(100);
	}	
	public void twoKey(int key1,int key2) throws InterruptedException{
		keyPress(key1);
		keyPress(key2);
		Thread.sleep(100);
		keyRelease(key1);
		keyRelease(key2);
	}
	public void qqSend() throws InterruptedException{
		twoKey(KeyEvent.VK_ALT,KeyEvent.VK_S);
	}
	public void inputNumber(String number){
		for (int i = 0; i < number.length(); i++) {
			char c=number.charAt(i);
			inputChar(c);
		}
	}
	public String nextNumber(String number){
		int i=Integer.parseInt(number);
		return String.valueOf(++i);
	}
	public String getMessage(int sX,int sY,int eX,int eY) throws Exception{
		mouseMove(sX,sY);
		this.mousePress(MouseEvent.BUTTON1_MASK);
		mouseFromTo(sX,sY,eX,eY);
//		mouseMove(sX, sY);
//		mouseMove(eX, eY);
		this.mouseRelease(MouseEvent.BUTTON1_MASK);
		Thread.sleep(500);//机器人的所有行为最好有个间隔时间，要不然好容易出错
		copy();
		String content=ClipboardOperate.getClipboardText();
		return content;
	}
	public void mouseFromTo(int sX,int sY,int eX,int eY) throws InterruptedException{
		//模拟人手慢慢移动的特性
		int totalTime=500;
		int count=100;int x,y;
		int timeSpace=totalTime/count;
		float dX=(eX-sX)/(float)count;
		float dY=(eY-sY)/(float)count;
		for (int i = 0; i <= count; i++) {
			long currentTime=System.currentTimeMillis();
			x=(int) (sX+dX*i);
			y=(int) (sY+dY*i);
//			if(i==100){
//				System.out.println(i);
//			}
//			System.out.println(x+" "+y);
			mouseMove(x, y);
			long nowTime=System.currentTimeMillis();
			if((nowTime-currentTime)<timeSpace){
				Thread.sleep(timeSpace-(nowTime-currentTime));
			}
		}
	}
	public void moveAndClick(int x,int y){
		mouseMove(x, y);
		mouseLeftClick();
	}
	public static void main(String[] args) throws Exception {
		TextRobot robot=new TextRobot();
		robot.qiehuan();
//		for (int i = 0; i <500; i++) {
//			robot.paste();
//			robot.qqSend();
//		}
		for (int i = 1030; i <1070; i++) {
			String s=String.valueOf(i);
			robot.inputNumber(s);
			robot.qqSend();
		}
		System.out.println("ok");
	}
}
