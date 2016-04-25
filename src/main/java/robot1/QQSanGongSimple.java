package robot1;

import exception.BasicException;
import utils.HttpUtil;
import utils.JsonUtil;
import web.message.ResponseMsg;

/**
 * 每次投50
 * @author Administrator
 *
 */
public class QQSanGongSimple extends QQSanGong{

	public QQSanGongSimple() throws Exception {
		super();
	} 
	//查询预测服务
	public int queryML(){
		String pa="{}";
		String url="http://127.0.0.1:8080/jsse-ml/brain/sgPredict";
		try {
			String res=HttpUtil.post(url, pa);
			System.out.println(res);
			ResponseMsg msg=(ResponseMsg) JsonUtil.json2ob(res, ResponseMsg.class);
			return (int)(double) msg.getData();
		} catch (BasicException e) {
			e.printStackTrace();
		}
		return 1;
	}
	public int stake(){
		//要赢赌100，要输赌50
		if(queryML()==1){
			return 100;
		}else{
			return 50;
		}
	}

	@Override
	public void win() {
		everyInvestMoney=stake();
	}

	@Override
	public void lose() {
		everyInvestMoney=stake();
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
//		sg.start();
		System.out.println(sg.queryML());
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
