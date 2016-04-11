package swing.operation;

import java.awt.event.WindowEvent;
import java.util.Date;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseWheelEvent;

import exception.ErrorException;
import utils.FileUtil;

public class OperationMain extends ABasic{
	Reappear reappear=new Reappear();
	StringBuffer sb=new StringBuffer();
	
	private void store(String msg){ 
//		sb.append(msg+"\n");
		long t;
		try { 
			t=getCurTime();
		} catch (ErrorException e) {
			throw new RuntimeException(e.getMessage());
		}
		msg+=",currentTime="+t;
		FileUtil.writeAdd(path, msg+"\n");
	}
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		store(e.paramString());
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		store(e.paramString());		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		store(e.paramString());		
	}
	@Override	
	public void nativeMouseClicked(NativeMouseEvent e) {
		store(e.paramString());		
	}
	@Override
	public void nativeMousePressed(NativeMouseEvent e) {
		store(e.paramString());		
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		store(e.paramString());		
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
		store(e.paramString());		
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		store(e.paramString());		
	}

	@Override
	public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		store(e.paramString());		
	}

	@Override
	public void reappearStart() throws Exception {
		reappear.openFile(null);
		reappear.action();
	}

	@Override
	public void reappearStop() throws Exception {
		reappear.stop();
	}
	public static void main(String[] args) {
		OperationMain o=new OperationMain();
		try {
			o.recordStart();
			Thread.sleep(20000);
			o.recordStop();
			o.reappearStart();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
