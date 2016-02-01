package webmagic;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import exception.BasicException;
import utils.DateUtil;
import utils.HttpUtil;

public class DownloadTest {
	@Test
	public void pic(){
		String url="http://www.allinpay.com/uploads/uploadPic/l/20151127,09,45,313.jpg";
		try {
//			String res=HttpUtil.get(url); 
			InputStream is=HttpUtil.getInputStream(url);
			ByteArrayOutputStream  bos=new ByteArrayOutputStream ();
			byte[] buffer=new byte[1024];
			int len=0;
			while((len=is.read(buffer))!=-1){
				bos.write(buffer, 0, len);
			}
			is.close();
			byte[] bytes=bos.toByteArray();
//			byte[] bytes=res.getBytes();
			String fullName=DateUtil.getNowFormat("yyy-MM-dd-HH-mm-ss")+"-"+Math.random()*1000+".png";
			FileOutputStream fos=new FileOutputStream(""+fullName);
			fos.write(bytes);
			fos.flush();
			fos.close();
		} catch (BasicException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.hint());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
