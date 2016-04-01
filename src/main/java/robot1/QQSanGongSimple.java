package robot1;
/**
 * 每次投50
 * @author Administrator
 *
 */
public class QQSanGongSimple extends QQSanGong{

	public QQSanGongSimple() throws Exception {
		super();
	} 

	@Override
	public void win() {
		everyInvestMoney=50;
	}

	@Override
	public void lose() {
		everyInvestMoney=50;
	} 
	@Override
	protected void relax() throws InterruptedException {
		Thread.sleep(2000);
	}
	int count=100;
	@Override
	public void play() {
		try {
			work();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		QQSanGongSimple sg;
		sg = new QQSanGongSimple();
		sg.start();
	}

	@Override
	public void threadRunning() {
		play();
	}

	@Override
	public void ready() {
		// TODO Auto-generated method stub
		
	}
}
