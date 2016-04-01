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
import robot1.QQChengYu;
import robot1.QQSanGongSimple;
import swing.resolve.ISwitch;
import swing.resolve.MsgQueue;

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
	HttpServer msgServer;
	KeypadRecorder keypadRecorder;
	//相关对象
	ISwitch sangong;
	ISwitch chengyu;
	public OSHelper(){
		init();
		prepare();
	}
	private void init() {
		httpServer=new HttpServer(17777);
		msgServer=new HttpServer(17778);
		keypadRecorder=new KeypadRecorder();
		try {
			sangong=new QQSanGongSimple();
			chengyu=new QQChengYu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void prepare() {
		setSize(width,height);
		backGround();
		systemTray();
	}
	boolean isKeyPadStart=false;
	boolean isHttpServerStart=false;
	boolean isMsgServerStart=false;
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
					httpServerStart();msgServerStart();
				}
			});
			menu.add(item);
			item=new MenuItem("关");
			item.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					httpServerStop();msgServerStop();
				}
			});
			menu.add(item);
			popupMenu.add(menu);
			//qq小云
			menu=new Menu("QQ小云");
			item=new MenuItem("三公开");
			item.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					sanGongStart();
				}
			});
			menu.add(item);
			item=new MenuItem("三公关");
			item.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					sanGongStop();
				}
			});
			menu.add(item);
			item=new MenuItem("成语开");
			item.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					chengyuStart();
				}
			});
			menu.add(item);
			item=new MenuItem("成语关");
			item.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					chengyuStop();
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
		MsgQueue.addMsg(s);
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
//			info("端口监听开启");
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
	public void msgServerStart(){
		if(isMsgServerStart) return;
		isMsgServerStart=true;
		msgServer.start();
		info("消息服务端口监听开启");
	}
	public void msgServerStop(){
		if(!isMsgServerStart) return;
		isMsgServerStart=false;
		msgServer.stop();
		info("消息服务端口监听关闭"); 
	}
	boolean isSanGongStart=false;
	boolean isChengYuStart=false;
	public void sanGongStart(){
		if(isSanGongStart) return;
		isSanGongStart=true;
		sangong.start();
		info("三公游戏开始");
	}
	public void sanGongStop(){
		if(!isSanGongStart) return;
		isSanGongStart=false;
		sangong.stop();
		info("三公游戏结束"); 
	}
	public void chengyuStart(){
		if(isChengYuStart) return;
		isChengYuStart=true;
		chengyu.start();
		info("成语游戏开始");
	}
	public void chengyuStop(){
		if(!isChengYuStart) return;
		isChengYuStart=false;
		chengyu.stop();
		info("成语游戏结束"); 
	}
	public void start(){
		setVisible(true);
		httpServerStart();
		keypadStart();
		msgServerStart();
		MsgQueue.addMsg("消息服务器工作正常");
	}
	public static void main(String[] args) {
//		OSHelper h=new OSHelper();
//		h.start();
	}
}
