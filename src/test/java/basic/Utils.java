package basic;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import utils.FileUtil;
import utils.JsonUtil;
import utils.LogUtil;

public class Utils {
	@Test
	public void json(){
		List<Map<String, String>> l=new ArrayList<Map<String,String>>();
		Map<String, String> m=new HashMap<String, String>();
		m.put("1", "tt");
		l.add(m);
		l.add(m);
		String json=JsonUtil.ob2json(l);
		System.out.println(json);
		JsonElement je=JsonUtil.json2je(json);
		JsonArray ja=je.getAsJsonArray();
		for (JsonElement jsonElement : ja) {
			JsonObject jo=jsonElement.getAsJsonObject();
			System.out.print(jo.get("1")+"  ");
			System.out.println();
		}
	}
	enum a {
		BLUE,
		YELLOW};
	enum code{
		SUCCESS("1"),FAIL("0");
		String value;
		code(String i){
			this.value=i;
		}
		public String getValue(){
			return this.value;
		}
		@Override
		public String toString(){
			return this.value;
		}
	}
	@Test
	public void enum_test(){
		for (a c : a.values()) {
			System.out.println(c);
		}
//		ResponseMsg msg=new ResponseMsg(code.SUCCESS, "123", "123");
		code d=code.SUCCESS;
		System.out.println(d);
		Map<String, Object> m=new HashMap<>(); 
		m.put("enum", d);
		System.out.println(JsonUtil.ob2json(m)); 
	}
	@Test
	public void gson(){
		System.out.println(JsonUtil.ob2json(new Date()));
	}
	@Test
	public void nm(){
		char ch = '杨';
		int i = ch;
//		Integer ii = new Integer(i);
		System.out.println( i );
		String st = Integer.toHexString(i);
		System.out.println(ch + " unicode is: \\u" + st);
		//System.out.println("\u5220\u9664");
	}
	@Test
	public void code() throws UnsupportedEncodingException{
		String s="杨";
		 String ns=new String(s.getBytes("unicode"),"GBK");
		 System.out.println(ns);
	}
	@Test()
	public void fw(){
		FileUtil.writeAdd("f:/fw.txt", "hello\r\n");
		FileUtil.writeAdd("f:/fw.txt", "hello\r\n");
	}
	@Test()
	public void log() throws IOException{
//		FileUtil.createFile("f:/t/");
//		FileUtil.createFile("f:/t1/1.txt");
		LogUtil.logDaily("h");
		LogUtil.logDaily("h");
		LogUtil.logDaily("h");
//		File f=new File("f:/t");
//		f.mkdirs();
	}
}
