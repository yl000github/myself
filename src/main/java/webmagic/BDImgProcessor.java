package webmagic;

import javax.print.attribute.Size2DSyntax;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class BDImgProcessor implements PageProcessor{
	private Site site=Site.me().setRetryTimes(3).setSleepTime(100);
	
	@Override
	public void process(Page page) {
		
		
	}

	@Override
	public Site getSite() {
		return site;
	}

}
