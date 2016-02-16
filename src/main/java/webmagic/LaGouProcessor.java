package webmagic;

import java.util.Set;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class LaGouProcessor implements PageProcessor{
	private Site site=Site.me().setRetryTimes(3).setSleepTime(100);

	@Override
	public void process(Page page) {
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
}
