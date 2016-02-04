package webmagic;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.selector.thread.CountableThreadPool;

public class AndroidImgProcessor implements PageProcessor{
	private Site site=Site.me().setRetryTimes(3).setSleepTime(100);
	public static String url="http://sj.zol.com.cn/bizhi/2/";
	int count=2;
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		if(count--<0) return;
		List<String> requestStrings=page.getHtml().links().regex("/bizhi/2/\\d.html").all();
		page.addTargetRequests(requestStrings);
//		Selectable s=page.getHtml().xpath("a[@class='pic']/img");
		Selectable s=page.getHtml().css("a.pic img","src");
		page.putField("imgUrls", s.all());
//		System.out.println(s.all().toString());
//		;
//		List<String> imgUrls=new ArrayList<>();
	}  
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	public static void main(String[] args) {
		Spider.create(new AndroidImgProcessor()).addUrl(url).
		addPipeline(new ConsolePipeline()).addPipeline(new ImagePipeline("F:/spiders/android"))
		.thread(5).start();
	}
}
