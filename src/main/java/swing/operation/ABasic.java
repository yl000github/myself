package swing.operation;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;

import utils.DateUtil;
import utils.FileUtil;

public abstract class ABasic implements IHook,IHandle{
	String path;
	Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
	public ABasic(){
		logger.setLevel(Level.OFF);
		logger.setUseParentHandlers(false);
	}
	@Override
	public void recordStart() throws Exception {
		path="d:/logs/operation "+DateUtil.getNowFormatD()+".txt";
		FileUtil.createFile(path);
		GlobalScreen.registerNativeHook();
		GlobalScreen.addNativeKeyListener(this);
		GlobalScreen.addNativeMouseListener(this);
		GlobalScreen.addNativeMouseMotionListener(this);
		GlobalScreen.addNativeMouseWheelListener(this);
	}
	@Override
	public void recordStop() throws Exception {
		GlobalScreen.unregisterNativeHook();
	}
}
