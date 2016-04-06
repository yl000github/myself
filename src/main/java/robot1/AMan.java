package robot1;

import swing.resolve.ISwitch;
import swing.resolve.MsgQueue;

public abstract class AMan extends Action implements IBehavior,ISwitch,Runnable{
	String content="";
	public AMan() throws Exception {
		super();
	}
	public void work() throws Exception{
		watch();
		think();
		action();
		relax();
	}
	protected void relax() throws InterruptedException{
		Thread.sleep(3000);
	}
	protected void read(int sX, int sY, int eX, int eY) throws Exception{
		content=getMessage(sX, sY, eX, eY);
	}
	/**
	 * 开始之前的准备工作
	 */
	public abstract void prepare();
	@Override
	public void start() {
		prepare();
		isRunning=true;
		new Thread(this).start();
	}
	@Override
	public void stop() {
		isRunning=false;
	}
	boolean isRunning=true;
	@Override
	public void run() {
		while(isRunning){
			threadRunning();
		}
		MsgQueue.addMsg("线程终止");
	}
	public abstract void threadRunning();
	public void info(String msg){
		System.out.println(msg);
		MsgQueue.addMsg(msg);
	}
}
