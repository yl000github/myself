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
	public void ready() {
		
	}
	int count=2;
	@Override
	public void play() {
		while(count-->0){
			try {
				work();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws Exception {
		QQSanGongSimple sg=new QQSanGongSimple();
		sg.play();
	}
}
