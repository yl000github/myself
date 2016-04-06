package swing.operation.event;

public class MouseMove  extends AEvent{
	int x,y;
	int button;
	int clickCount;
	public MouseMove(){
		flag="NATIVE_MOUSE_MOVED";
	}
	
	@Override
	public boolean consume(String msg) throws Exception {
		if(!msg.contains(flag)) return false;
		int[] pos=getPosition(msg);
		x=pos[0];y=pos[1];
		return true;
	}

}
