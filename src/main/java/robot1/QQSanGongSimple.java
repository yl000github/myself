package robot1;
/**
 * 每次投50
 * @author Administrator
 *
 */
public class QQSanGongSimple extends QQSanGong{

	public QQSanGongSimple() throws Exception {
		super();
		inputText("==============");
		try {
			qqSend();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	public void ready() {}
	int count=100;
	@Override
	public void play() {
		while(count-->0){
			try {
				work();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			watch();
			think();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		QQSanGongSimple sg;
		try {
			sg = new QQSanGongSimple();
			sg.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
