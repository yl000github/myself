package swing.operation;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import exception.ErrorException;
import exception.InfoException;
import swing.operation.event.*;

public class Reappear{
	IConsume [] consumers;
	public Reappear() {
		consumers=new IConsume[]{
				new MousePress(),new MouseRelease(),	
				new MouseMove(),new MouseWheel(),	
				new MouseClick(),new MouseDrag(),
			new KeyPress(),new KeyRelease(),	
			new KeyClick()
		};
	}
	Date startTime;
	protected long getCurTime() throws ErrorException{
		if(startTime==null) throw new ErrorException("起始时间未初始化");
		return new Date().getTime()-startTime.getTime();
	}
	String path;
	private String openChooseFile(){
		// 创建文件选择器
		JFileChooser fileChooser = new JFileChooser();
		// 设置当前目录
		fileChooser.setCurrentDirectory(new File("d:/logs"));
		fileChooser.setAcceptAllFileFilterUsed(false);
//		final String[][] fileENames = { { ".java", "JAVA源程序 文件(*.java)" }, { ".doc", "MS-Word 2003 文件(*.doc)" },
//				{ ".xls", "MS-Excel 2003 文件(*.xls)" } };
		final String[][] fileENames = { { ".txt", "文本文件(*.txt)" } };
		// 显示所有文件
		fileChooser.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File file) {
				return true;
			}
			public String getDescription() {
				return "所有文件(*.*)";
			}
		});
		// 循环添加需要显示的文件
		for (final String[] fileEName : fileENames) {
			fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File file) {
					if (file.getName().endsWith(fileEName[0]) || file.isDirectory()) {
						return true;
					}
					return false;
				}
				public String getDescription() {
					return fileEName[1];
				}
			});
		}
		fileChooser.showDialog(null, null);
		return fileChooser.getSelectedFile().getPath();
//		System.out.println(fileChooser.getSelectedFile().getPath());
	}
	public void openFile(String path) throws Exception{
		if(path==null){
			this.path=openChooseFile();
		}else{
			this.path=path;
		}
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
		startTime=new Date(); 
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
	public boolean consumeLine(String msg) throws Exception{
		String time=getValue(msg,"currentTime");
		Long t=Long.parseLong(time);
		long cur=getCurTime();
		if(t>cur){
			Thread.sleep(t-cur);
		}else{ 
			startTime=new Date(startTime.getTime()+cur-t);
		}
		for (int i = 0; i < consumers.length; i++) {
			try {
				if(consumers[i].consume(msg)) return true;
			} catch (Exception e) {
				//有问题，继续,罗列原因
			}
		}
		throw new ErrorException("该行未被处理："+msg);
	}
	public String getValue(String msg,String key) throws InfoException{
		int keyIndex=msg.indexOf(key);
		if(keyIndex==-1) throw new InfoException("找不到"+key);
		int commaIndex=msg.substring(keyIndex).indexOf(",");
		if(commaIndex!=-1){
			//表明不是最后一组
			String value=msg.substring(keyIndex+key.length()+1, keyIndex+commaIndex);
			return value;
		}else{
			//表明是最后一组
			String value=msg.substring(keyIndex+key.length()+1, msg.length());
			return value;
		}
	}
	public void stop() {
		
	}
}
