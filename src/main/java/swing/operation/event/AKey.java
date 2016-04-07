package swing.operation.event;

import java.awt.event.KeyEvent;

public class AKey extends AEvent{
	int keyCode;
	String keyText;
	String keyChar;
	String keyLocation;
	int rawCode;
	@Override
	public boolean consume(String msg) throws Exception {
		super.consume(msg);
		keyText=getValue(msg, "keyText");
		keyChar=getValue(msg, "keyChar");
		keyLocation=getValue(msg, "keyLocation");
		keyCode=Integer.parseInt(getValue(msg, "keyCode"));
		rawCode=Integer.parseInt(getValue(msg, "rawCode"));
		return true;
	}
	protected int keymapping(){
		if(keyText.equals("Esc")){
			return KeyEvent.VK_ESCAPE;
		}
		if(keyText.equals("后引号")){
			return KeyEvent.VK_BACK_QUOTE;
		}
		if(keyText.equals("Tab")){
			return KeyEvent.VK_TAB;
		}
		if(keyText.equals("Caps Lock")){
			return KeyEvent.VK_CAPS_LOCK;
		}
		if(keyText.equals("Left Shift")){
			return KeyEvent.VK_SHIFT;
		}
		if(keyText.equals("Left Control")){
			return KeyEvent.VK_CONTROL;
		}
		if(keyText.equals("Left Meta")){
			return KeyEvent.VK_META;
		}
		if(keyText.equals("Left Alt")){
			return KeyEvent.VK_ALT;
		}
		if(keyText.equals("空格")){
			return KeyEvent.VK_SPACE;
		}
		if(keyText.equals("Right Alt")){
			return KeyEvent.VK_ALT;
		}
		if(keyText.equals("Print Screen")){
			return KeyEvent.VK_PRINTSCREEN;
		}
		if(keyText.equals("Right Control")){
			return KeyEvent.VK_CONTROL;
		}
		if(keyText.equals("逗号")){
			return KeyEvent.VK_COMMA;
		}
		if(keyText.equals("句点")){
			return KeyEvent.VK_PERIOD;
		}
		if(keyText.equals("斜杠")){
			return KeyEvent.VK_SLASH;
		}
		if(keyText.equals("分号")){
			return KeyEvent.VK_SEMICOLON;
		}
		if(keyText.equals("引号")){
			return KeyEvent.VK_QUOTE;
		}
		if(keyText.equals("左方括号")){
			return KeyEvent.VK_OPEN_BRACKET;
		}
		if(keyText.equals("右方括号")){
			return KeyEvent.VK_CLOSE_BRACKET;
		}
		////TODO
		if(keyText.equals("Esc")){ 
			return KeyEvent.VK_ESCAPE;
		}
		if(keyText.equals("Esc")){
			return KeyEvent.VK_ESCAPE;
		}
		if(keyText.equals("Esc")){
			return KeyEvent.VK_ESCAPE;
		}
		if(keyText.equals("Esc")){
			return KeyEvent.VK_ESCAPE;
		}
		if(keyText.equals("Esc")){
			return KeyEvent.VK_ESCAPE;
		}
		return rawCode;
	}
}
