package omp.backtest;

import exception.BasicException;

public class BackTest extends BackBase{
	public BackTest(){
		path="d:/test/quotation-test.txt";
		url="http://localhost:8080/jsse/orderManager/listQuotation";
	}
	public static void main(String[] args) {
		BackTest t=new BackTest();
		t.readLib();
		t.test();
//		try {
//			System.out.println(t.http("http://localhost:8080/jsse/test/hello", "{}"));
//		} catch (BasicException e) {
//			e.printStackTrace();
//		}
	}
}
