package swing.operation.event;

import java.awt.AWTException;
import java.awt.Robot;

import exception.InfoException;

public abstract class AEvent implements IConsume{
	Robot robot;
	public AEvent(){
		try {
			robot=new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	protected String flag;
	@Override
	public boolean consume(String msg) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	public int[] getPosition(String msg) throws InfoException{
		int[] res=new int[2];
		String pos=msg.substring(msg.indexOf("(")+1,msg.indexOf(")"));
		String []r=pos.split(",");
		if(r.length!=2) throw new InfoException("获取position出错");
		for (int i = 0; i < r.length; i++) {
			res[i]=Integer.parseInt(r[i]);
		}
		return res;
	}
}
