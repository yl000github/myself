package webmagic;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import exception.BasicException;
import utils.HttpUtil;
import utils.JsonUtil;
import utils.XlsUtil;

/**
 * 使用httpclient模拟ajax请求，自己解析
 * @author Yang
 *
 */
public class LaGouAjax {
//	String url="";
	public static void main(String[] args){
		
		String url="http://www.lagou.com/jobs/positionAjax.json?city=深圳";
		String key="java";
		Map<String,String> map=new HashMap<>();
		for (int i = 1; i < 33; i++) {
			if(i==1){
				map.put("first", "true");
				map.put("pn", String.valueOf(i));
				map.put("kd", key);
			}else{
				map.put("first", "false");
				map.put("pn", String.valueOf(i));
				map.put("kd", key);
			}
			try {
				String result=HttpUtil.post(url, map);
				System.out.println("第"+i+"个result：");
				parse(result);
			} catch (BasicException e) {
				System.out.println(e.hint());
			}
		} 
	}
	public static String[] wantedKey={
			"city","companyName","companySize","createTime",
			"education","finaceStage","industryField","jobNature",
			"leaderName","positionAdvantage","positionName","salary",
			"workYear"
	};
	private static void parse(String result) {
		JsonElement json=JsonUtil.json2je(result);
		JsonObject jo=json.getAsJsonObject();
		if(jo.get("success").getAsBoolean()){
			JsonArray ja=jo.get("content").getAsJsonObject().get("result").getAsJsonArray();
			List<List<String>> lists=new ArrayList<>();
			for (JsonElement jet : ja) {
				List<String> list=new ArrayList<>();
				JsonObject jot=jet.getAsJsonObject();
				for (String key : wantedKey) {
					String value;
					if(jot.get(key)==null){ 
						value="";
					}else{
						value=jot.get(key).getAsString();
					}
					list.add(value);
				}
				lists.add(list);
			}  
			try {
				File f=new File("F:/lagou.xlsx");
				if(f.exists()){
					XlsUtil.writeAdd("F:/lagou.xlsx", lists);
				}else{
					List<String> titles=Arrays.asList(wantedKey);
					XlsUtil.write("F:/lagou.xlsx",titles,lists);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("success值为false");
		}
	}
}
