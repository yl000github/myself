package webmagic;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {
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
    public void testSelenium() {
        System.getProperties().setProperty("webdriver.chrome.driver", "D:/download/chromedriver_win32/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://huaban.com/");
        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        System.out.println(webElement.getAttribute("outerHTML"));
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
