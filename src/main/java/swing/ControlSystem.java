package swing;

/**
 * 最高权限，程序的入口
 * @author Administrator 
 * 
 */
public class ControlSystem {  
	OSHelper helper;
	private static ControlSystem instance;
	String openFile;
	
	public String getOpenFile() {
		return openFile;
	}
	public void setOpenFile(String openFile) {
		this.openFile = openFile;
	}
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
		if(args.length!=0){
			String firstPara=args[0];
			if(firstPara.equals("reappearsg")){
				try {
					//TODO
					String path="d:/logs/sg.txt";
					ControlSystem.getInstance().setOpenFile(path);
					ControlSystem.getHelper().getOperationHandle().reappearStart();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
