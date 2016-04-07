package swing.operation;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;

import exception.ErrorException;
import swing.operation.event.*;

public class Reappear{
	IConsume [] consumers;
	public Reappear() {
		consumers=new IConsume[]{
				new MousePress(),new MouseRelease(),	
				new MouseMove(),new MouseWheel(),	
				new MouseClick(),
			new KeyPress(),new KeyRelease(),	
			new KeyClick()
		};
	}
	String path;
	public void openFile(String path) throws Exception{
		this.path=path;
	}
	//因为模拟恐怕要有少许停顿
	private void pause(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
	}
	//耗时任务
	public void action() throws Exception{
		Reader r=new FileReader(path);
		BufferedReader reader=new BufferedReader(r);
		String msg;
		while((msg=reader.readLine())!=null){
			try { 
				consumeLine(msg);
			} catch (Exception e) {
				//有问题的行先仅输出
				e.printStackTrace();
			}
		}
		reader.close();
	}
	private boolean consumeLine(String msg) throws Exception{
		for (int i = 0; i < consumers.length; i++) {
			try {
				if(consumers[i].consume(msg)) return true;
			} catch (Exception e) {
				//有问题，继续,罗列原因
			}
		}
		throw new ErrorException("该行未被处理："+msg);
	}
	public void stop() {
		
	}
}
