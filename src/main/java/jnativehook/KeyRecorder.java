package jnativehook;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import org.jnativehook.keyboard.NativeKeyListener;

import utils.LogUtil;

/**
 * 监听键盘，存到一个指定文件里面去，这个数据以后可以试着分析
 * 做一个swing的简单界面
 * @author Yang
 *
 */
public class KeyRecorder extends JFrame implements NativeKeyListener{
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		String key=NativeKeyEvent.getKeyText(e.getKeyCode());
		LogUtil.logDaily(key+" ");
	}
	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//swing 相关
	private static final long serialVersionUID = 1L;
	JButton button;
	JLabel info;
	SystemTray tray;
	TrayIcon trayIcon;
	public KeyRecorder(String title){
		super(title);
		try {
			prepare();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void prepare() throws AWTException{
		setSize(500, 500);
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
            System.out.println(url.getPath());
            System.out.println(url.toString());
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
		try {
			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeKeyListener(this);
		} catch (NativeHookException e) {
			e.printStackTrace();
			info.setText("注册钩子失败");
		}
	}
	
	public void stop(){
		try {
			GlobalScreen.unregisterNativeHook();
		} catch (NativeHookException e) {
			e.printStackTrace();
			info.setText("注销钩子失败");
		}
	}
	
	
	public static void main(String[] args) {
		KeyRecorder keyRecoder=new KeyRecorder("键盘监听器");
		keyRecoder.setVisible(true);
	}
}
