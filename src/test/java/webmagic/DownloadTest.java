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
		String url="http://img1.imgtn.bdimg.com/it/u=676278492,3212497429&fm=21&gp=0.jpg";
		try {
			byte[] bytes=HttpUtil.getBytes(url);
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
