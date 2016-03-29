package robot1;

public abstract class AMan extends Action implements IBehavior{
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
		Thread.sleep(5000);
	}
	protected void read(int sX, int sY, int eX, int eY) throws Exception{
		content=getMessage(sX, sY, eX, eY);
	}
}
