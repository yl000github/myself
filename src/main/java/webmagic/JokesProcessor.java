package webmagic;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class JokesProcessor implements PageProcessor{

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
//    	page.addTargetRequests(page.getHtml().links().regex("(https://www\\.com/\\w+/\\w+)").all());
        page.addTargetRequests(page.getHtml().links().regex("http://www.ihuopo\\.com/joke/new_\\w+.html").all());
        List<String> lists=page.getHtml().xpath("//div[@class='content']/allText()").all();
//        List<String> l=new ArrayList<>();
//        for (Selectable selectable : lists) {
//        	String info=selectable.
//			l.add(selectable.get()); 
//		}
        page.putField("jokes",lists );
    } 
    @Override
    public Site getSite() { 
        return site;
    }
  
    public static void main(String[] args) {
        Spider.create(new JokesProcessor()).addUrl("http://www.ihuopo.com/").addPipeline(new JokesFilePipeline("F:/webmagic/jokes.txt")).thread(1).run();
    }
}
