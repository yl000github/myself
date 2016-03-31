package swing;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import exception.InfoException;

/**
 * ui
 * @author Administrator
 *
 */
public class OSHelper extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int width=200;
	int height=200;
	JLabel back;
	SystemTray tray;TrayIcon trayIcon;
//	String menus="["
//			+ "{\"title\":\"设置\",\"type\":\"menu\",\"sort\":\"0\",\"data\":\"{\"title\":\"名称\",\"type\":\"item\",\"sort\":\"0\",\"data\":\"traySet\"}\",}"
//			+ "{\"title\":\"键盘监听\",\"type\":\"menu\",\"sort\":\"1\",\"data\":\"{\"title\":\"打开\",\"type\":\"item\",\"sort\":\"0\",\"data\":\"trayOpen\"}\",}"
//			+ "]";
	HttpServer httpServer;
	KeypadRecorder keypadRecorder;
	public OSHelper(){
		init();
		prepare();
	}
	private void init() {
		httpServer=new HttpServer();
		keypadRecorder=new KeypadRecorder();
	}
	private void prepare() {
		setSize(width,height);
		backGround();
		systemTray();
	}
	boolean isKeyPadStart=false;
	boolean isHttpServerStart=false;
	private void systemTray() {
//		Map<String,Menu> m=new HashMap<>();
		if(SystemTray.isSupported()){
			PopupMenu popupMenu=new PopupMenu();
			Menu menu;MenuItem item;
			//设置
			menu=new Menu("设置");
			menu.add(new MenuItem("开"));
			popupMenu.add(menu);
			//打开主界面
			item=new MenuItem("打开主界面");
			item.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(true);
				}
			});
			popupMenu.add(item);
			
			//键盘监听
			menu=new Menu("键盘监听");
			item=new MenuItem("开");
			item.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					keypadStart();
				}
			});
			menu.add(item);
			item=new MenuItem("关");
			item.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					keypadStop();
				}
			});
			menu.add(item);
			popupMenu.add(menu);
			//监听端口
			menu=new Menu("端口监听");
			item=new MenuItem("开");
			item.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					 httpServerStart();
				}
			});
			menu.add(item);
			item=new MenuItem("关");
			item.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					httpServerStop();
				}
			});
			menu.add(item);
			popupMenu.add(menu);
			
			//退出
			item=new MenuItem("退出");
			item.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(1);
				}
			});
			popupMenu.add(item);
			/////////////////////////////////////
			
			ClassLoader cl = this.getClass().getClassLoader(); 
            URL url = cl.getResource("pic/face.png"); 
            ImageIcon icon = new ImageIcon(url);  
            trayIcon = new TrayIcon(icon.getImage(), "键盘监听", popupMenu); 
            //获取托盘菜单 ,这句话是最关键的
            tray = SystemTray.getSystemTray(); 
            try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				e.printStackTrace();
			}  
			
		}else{ 
			info("系统不支持");
		}
	}
	private void info(String s){
		System.out.println(s);
	}
	private void backGround() {
		back=new JLabel();
		 // 设置背景图片
        int h = this.getHeight();
        int w = this.getWidth(); 
        ClassLoader cl = this.getClass().getClassLoader(); 
        URL url = cl.getResource("pic/fruit.jpg"); 
        String imagePath=url.toString();
        back
                .setText("<html><body><image width='"
                        + w
                        + "' height='"
                        + h
                        + "' src="
                        + imagePath
                        + "'></img></body></html>");
 
        this.add(back, BorderLayout.CENTER);
	}
	public void keypadStart(){
		if(isKeyPadStart) return;
		isKeyPadStart=true;
		try {
			keypadRecorder.start();
			info("端口监听开启");
		} catch (InfoException e1) {
			info(e1.getMessage());
		}
	}
	public void keypadStop(){
		if(!isKeyPadStart) return;
		isKeyPadStart=false;
		try {
			keypadRecorder.stop();
			info("键盘监听关闭");
		} catch (InfoException e1) {
			info(e1.getMessage());
		}
	}
	public void httpServerStart(){
		if(isHttpServerStart) return;
		isHttpServerStart=true;
		httpServer.start();
		info("端口监听开启");
	}
	public void httpServerStop(){
		if(!isHttpServerStart) return;
		isHttpServerStart=false;
		httpServer.stop();
		info("端口监听关闭"); 
	}
	public static void main(String[] args) {
		OSHelper h=new OSHelper();
		h.setVisible(true);
		h.httpServerStart();
		h.keypadStart();
	}
}
