package swing.operation;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import exception.ErrorException;
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
