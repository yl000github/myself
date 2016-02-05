package webmagic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import us.codecraft.webmagic.ResultItems;

public class Selenium {
	//actions 可以模仿鼠标和键盘的操作
	@Before
	public void before(){
		 System.getProperties().setProperty("webdriver.chrome.driver", "D:/download/chromedriver_win32/chromedriver.exe");
	}
	@Test
	public void sp(){
		WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://localhost/learning/html/hello.html");
        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        System.out.println(webElement.getAttribute("outerHTML"));
        webDriver.close();
	}
	@Test
	public void idNumber() throws InterruptedException{
		WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://shenfenzheng.293.net/");
        WebElement input = webDriver.findElement(By.xpath("/html//form[@id='forma']//input[@name='id']"));
//        System.out.println(input.getAttribute("outerHTML"));
        input.sendKeys("42092119931108461x");
        WebElement button=webDriver.findElement(By.xpath("/html//form[@id='forma']//input[@type='submit']"));
//        System.out.println(button.getAttribute("outerHTML"));
        button.click();
        Thread.sleep(2000);
        WebElement ano=webDriver.findElement(By.xpath("/html//table[@id='show_table']"));
//        System.out.println(ano.getAttribute("outerHTML"));
        System.out.println(ano.getText()); 
        webDriver.close();
	}
  @Test
    public void testSelenium() {
        System.getProperties().setProperty("webdriver.chrome.driver", "D:/download/chromedriver_win32/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://huaban.com/");
        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        System.out.println(webElement.getAttribute("outerHTML"));
        webDriver.close();
    }
  //使用时要在httputil中加上referer
  	@Test
  	public void bdImg() throws InterruptedException{
  		 WebDriver webDriver = new ChromeDriver();
         webDriver.get("http://image.baidu.com/");
         WebElement input = webDriver.findElement(By.xpath("/html//input[@id='kw']"));
         WebElement button=webDriver.findElement(By.xpath("/html//input[@type='submit']"));
         input.sendKeys("海水");
         button.click();
         ((JavascriptExecutor) webDriver).executeScript("scroll(0,3000);");
         ((JavascriptExecutor) webDriver).executeScript("scroll(0,3000);");
         ((JavascriptExecutor) webDriver).executeScript("scroll(0,3000);");
         Thread.sleep(4000);
         List<WebElement> imgs=webDriver.findElements(By.xpath("//img[@class='main_img img-hover']"));
//         System.out.println(imgUrls);
         List<String> imgUrls=new ArrayList<>();
         for (WebElement e : imgs) {
			String src=e.getAttribute("src");
			imgUrls.add(src);
		}
         System.out.println(imgUrls.toString());
         ResultItems result=new ResultItems();
         result.put("imgUrls",imgUrls);
         ImagePipeline pipe=new ImagePipeline("f:/spiders/bd");
         pipe.process(result, null);
         webDriver.close(); 
  	}
	 @Test
    public void testSelenium1() {
		try {
			System.getProperties().setProperty("webdriver.chrome.driver", "D:/download/chromedriver_win32/chromedriver.exe");
			WebDriver webDriver = new ChromeDriver();
//			webDriver.get("http://huaban.com/");
			webDriver.get("http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=index&fr=&sf=1&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word=%E6%B5%B7%E6%B0%B4&oq=%E6%B5%B7%E6%B0%B4&rsp=-1");
			WebElement webElement = webDriver.findElement(By.xpath("/html"));
			List<WebElement> imgs=webElement.findElements(By.tagName("img"));
			System.out.println(imgs.size()+"img found");
			for (WebElement img : imgs) {
				System.out.println(img.getAttribute("src"));
			}
//			System.out.println(webElement.getAttribute("outerHTML"));
			webDriver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
