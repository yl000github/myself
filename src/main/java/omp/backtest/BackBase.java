package omp.backtest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.BasicException;
import exception.ErrorException;
import utils.HttpUtil;
import utils.JsonUtil;

/**
 * 使用httpclient的post请求，读取测试用例，获取返回字段，如果出错就提示
 * @author Administrator
 *
 */
public abstract class BackBase {
	List<String> lib=new ArrayList<>();
	String path="";String url="";
	String resultPath;
	@SuppressWarnings({  "rawtypes" })
	protected String http(String url,String json) throws BasicException{
//		Map params= (Map) JsonUtil.json2ob(json, Map.class);
//		Map<String,String> p=new HashMap<>();
//		for (Object s : params.keySet()) {
//			String key=String.valueOf(s);
//			String value=String.valueOf(params.get(s));
//			p.put(key, value);
//		}
//		String res=HttpUtil.post(url, p);
		String res=HttpUtil.post(url, json);
		return res;
	}
	public void readLib(String path){
		lib.clear();
		try {
			BufferedReader br=new BufferedReader(new FileReader(path));
			String line;
			while((line=br.readLine())!=null){
				lib.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void readLib(){
		readLib(path);
	}
	public void test(){
		for (String line : lib) {
			try {
				String res=http(url, line);
				parse(res);
			} catch (ErrorException | BasicException e) {
				String msg="测试输入："+line+"\n"+e.getMessage();
				throw new RuntimeException(msg);
			}
		}
		System.out.println("all done,good job!");
	}
	private void parse(String res) throws ErrorException{
		if(res.contains("\"errorCode\":0")){
			System.out.println("test is ok");
		}else{
			throw new ErrorException("测试失败结果:"+res);
		}
	}
}
