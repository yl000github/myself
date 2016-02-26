package webmagic;

import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.PlainText;
import utils.FileUtil;

public class JokesFilePipeline implements Pipeline{
	String myPath;
	public JokesFilePipeline(String path){
		this.myPath=path;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void process(ResultItems resultItems, Task task) {
		Map<String,Object> map=resultItems.getAll();
		List<String>lists=(List<String>) map.get("jokes");
		for (int i = 0; i < lists.size(); i++) {
//			Object o=lists.get(i);
//			if(o instanceof PlainText){
//				System.out.println("plaintext");
//			}else if(o instanceof String){
//				System.out.println("String");
//				
//			}
			String str=lists.get(i);
			FileUtil.writeAdd(myPath, str+"\r\t");
			
		}
//		for (String str : lists) {
//			FileUtil.writeAdd(myPath, str+"\r\t");
//		}
	}
} 
