package webmagic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.spi.LoggerFactory;
import exception.BasicException;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.FilePipeline;
import utils.DateUtil;
import utils.HttpUtil;

public class ImagePipeline extends FilePipeline{
	Log log=LogFactory.getLog(getClass());
	public ImagePipeline(){
		super();
	}
	public ImagePipeline(String path){
		super(path);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void process(ResultItems resultItems, Task task) {
		Map<String, Object> map=resultItems.getAll();
		String imgUrl=(String) map.get("imgUrl");
		if(imgUrl!=null){
			storeImg(imgUrl);
		}else{
			log.info("imgUrl is null");
		}
		List<String> imgUrls=(List<String>) map.get("imgUrls");
		if(imgUrls!=null){
			for (String url : imgUrls) {
				storeImg(url);
			}
		}else{
			log.info("imgUrls is null");
		}
	}
	private void storeImg(String imgUrl){
		try {
			byte[] bytes=HttpUtil.getBytes(imgUrl);
			String fullName=DateUtil.getNowFormat("yyy-MM-dd-HH-mm-ss")+"-"+Math.random()*1000+".png";
			FileOutputStream fos=new FileOutputStream(getFile(getPath()+fullName));
			fos.write(bytes);
			fos.flush();
			fos.close();
		} catch (BasicException e) {
			e.printStackTrace();
			log.info(e.hint());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
