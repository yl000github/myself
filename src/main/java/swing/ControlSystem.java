package swing;

/**
 * 最高权限，程序的入口
 * @author Administrator
 *
 */
public class ControlSystem {  
	OSHelper helper;
	private static ControlSystem instance;
	private ControlSystem(){
		helper=new OSHelper();
	}
	public static ControlSystem getInstance(){
		if(instance==null){
			instance=new ControlSystem();
		}
		return instance;
	}
	public static OSHelper getHelper(){
		return getInstance()._getHelper();
	}
	public OSHelper _getHelper(){
		return helper;
	}
	public static void main(String[] args) {
		ControlSystem.getHelper().start();
	}
	
}
