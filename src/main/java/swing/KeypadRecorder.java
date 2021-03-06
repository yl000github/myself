package swing;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import exception.InfoException;
import utils.LogUtil;

public class KeypadRecorder implements NativeKeyListener{
	Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
	public KeypadRecorder(){
		// Get the logger for "org.jnativehook" and set the level to off.
		logger.setLevel(Level.OFF);
		// Don't forget to disable the parent handlers.
		logger.setUseParentHandlers(false);
	}
	public void shortCut(NativeKeyEvent e){
		String key=NativeKeyEvent.getKeyText(e.getKeyCode());
		if(key.equals("F2")){
			System.out.println("f2");
			ControlSystem.getHelper().sanGongStart();
		}else if(key.equals("F4")){
			System.out.println("f4");
			ControlSystem.getHelper().sanGongStop();
		}else if(key.equals("Esc")){
			System.out.println("esc");
			try {
				ControlSystem.getHelper().getOperationHandle().recordStop();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if(key.equals("F4")){
			
		}
	}
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		shortCut(e);
		String key=NativeKeyEvent.getKeyText(e.getKeyCode());
		LogUtil.logDailyKeyPad(key+" ");
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		
	}
	public void start() throws InfoException{
		try {
			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeKeyListener(this);
		} catch (NativeHookException e) {
			e.printStackTrace();
			throw new InfoException("注册钩子失败");
		}
	}
	
	public void stop() throws InfoException{
		try {
			GlobalScreen.unregisterNativeHook();
		} catch (NativeHookException e) {
			e.printStackTrace();
			throw new InfoException("注销钩子失败");
		}
	}
}
