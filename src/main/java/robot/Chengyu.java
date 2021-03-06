package robot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import exception.BasicException;

public class Chengyu {
	private static WebDriver webDriver = new HtmlUnitDriver();
	public static String getFirst(String head) throws BasicException{
		try {
//			WebDriver webDriver = new HtmlUnitDriver();
			webDriver.get("http://chengyu.t086.com/chaxun.php?q1=&q2=&q3=&q4=");
			WebElement input = webDriver.findElement(By.xpath("//input[@name='q1']"));
//			System.out.println(input.toString());
			input.sendKeys(head);
			WebElement button = webDriver.findElement(By.xpath("//input[@type='submit']"));
			button.click();
			// Thread.sleep(2000);
//			WebElement ano = webDriver.findElement(By.xpath("//html"));
			List<WebElement> ano = webDriver.findElements(By.xpath("//td[@class='td1']"));
			String ans="我日";
			for (WebElement w : ano) {
				String ts=w.getText();
				if(ts.length()==4){
					ans=ts;
					break;
				} 
			}
//			String ans=ano.getText();
//			System.out.println(ano.getText());
//			webDriver.close();
			return ans;
		}  catch (Throwable t) {
			t.printStackTrace();
			throw new BasicException("没有找到结果");
		}
//		return "没有找到";
	}
	public static Set<String> getFirstList(String head) throws BasicException{
		try {
			webDriver.get("http://chengyu.t086.com/chaxun.php?q1=&q2=&q3=&q4=");
			WebElement input = webDriver.findElement(By.xpath("//input[@name='q1']"));
			input.sendKeys(head);
			WebElement button = webDriver.findElement(By.xpath("//input[@type='submit']"));
			button.click();
			List<WebElement> ano = webDriver.findElements(By.xpath("//td[@class='td1']"));
			Set<String> ans=new HashSet<>();
			for (WebElement w : ano) {
				String ts=w.getText();
				if(ts.length()==4){
					ans.add(ts);
				}
			}
			return ans;
		}  catch (Throwable t) {
			t.printStackTrace();
			throw new BasicException("没有找到结果");
		}
	}
	public static void main(String[] args) throws BasicException {
		Chengyu c=new Chengyu();
		Set<String> ans=c.getFirstList("邦");
		for (String string : ans) {
			System.out.println(string);
		}
		System.out.println(ans);
	}
}
