package swing;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

import jnativehook.KeyRecorder;
import utils.LogUtil;

/**
 * 主要负责界面
 * @author Administrator
 *
 */
public class HttpListener extends JFrame{
	//swing 相关
	private static final long serialVersionUID = 1L;
	JButton button;
	JLabel info;
	SystemTray tray;
	TrayIcon trayIcon;
	//监听相关 
	HttpServer server;
	public HttpListener(String title){
		super(title);
		try {
			server=new HttpServer();
			prepare();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void prepare() throws AWTException{
		setSize(250, 250);
		BorderLayout layout=new BorderLayout();
//		layout.set
		setLayout(layout);
		button=new JButton("开启");
		button.setSize(100, 100);
		button.setFont(new Font("幼圆",0,32));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(button.getText().equals("开启")){
					start();
					button.setText("关闭");
				}else{
					stop();
					button.setText("开启");
				}
			}
		}); 
		add(button,BorderLayout.CENTER);
		info=new JLabel();info.setText("一切ok");
		add(info,BorderLayout.SOUTH);
		//system tray
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        addWindowListener(new WindowAdapter() { 
            @Override 
            public void windowClosing(WindowEvent e) { 
                setVisible(false); 
            } 
        });
        if (SystemTray.isSupported()) { //当前平台是否支持系统托盘 
            //创建一个右键弹出菜单 
            PopupMenu popupMenu = new PopupMenu(); 
            MenuItem mi = new MenuItem("打开主面板"); 
//            MenuItem mStart = new MenuItem("开启"); 
//            MenuItem mClose = new MenuItem("关闭"); 
            MenuItem exit = new MenuItem("退出"); 
            popupMenu.add(mi); 
            popupMenu.add(exit); 
            mi.addActionListener(new ActionListener() { 
                @Override 
                public void actionPerformed(ActionEvent e) { 
                    setVisible(true); 
                } 
   
            }); 
            exit.addActionListener(new ActionListener() { 
                @Override 
                public void actionPerformed(ActionEvent e) { 
                    System.exit(0); 
                } 
            }); 
            ClassLoader cl = this.getClass().getClassLoader(); 
            URL url = cl.getResource("pic/tray.png"); 
            ImageIcon icon = new ImageIcon(url);  
            //创建托盘图标   
            trayIcon = new TrayIcon(icon.getImage(), "键盘监听", popupMenu); 
            //获取托盘菜单 
            tray = SystemTray.getSystemTray(); 
            //添加托盘图标  
            tray.add(trayIcon);  
        }  
	}
	public void start(){
		System.out.println("开始监听");
		server.start();
//		System.out.println("hi");
	}
	public void stop(){
		System.out.println("结束监听");
		server.stop();
	}
	public void click(){
		button.doClick();
	}
	public static void main(String[] args) {
		HttpListener listener=new HttpListener("端口监听");
		listener.setVisible(true);
		listener.click();
	}
}
