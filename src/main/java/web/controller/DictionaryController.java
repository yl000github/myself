package web.controller;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.Affair;
import utils.StringUtil;
import web.message.ResponseMsg;

@Controller
public class DictionaryController {
	WebDriver webDriver;
	public DictionaryController(){
	}
	@ResponseBody
	@RequestMapping(value="/dictionary/youdao",produces = {"text/plain;charset=utf-8"})
	public String youdao(String value){
		try {
			System.getProperties().setProperty("webdriver.chrome.driver", "D:/download/chromedriver_win32/chromedriver.exe");
			 System.getProperties().setProperty("phantomjs.binary.path", "D:/download/phantomjs-2.1.1-windows/bin/phantomjs.exe");
			webDriver=new HtmlUnitDriver();
			if(!StringUtil.checkValid(value))  return new ResponseMsg(0, "value为空", "").toJson();
			webDriver.get("http://dict.youdao.com/");
			WebElement input = webDriver.findElement(By.xpath("//input[@id='query']"));
			input.sendKeys(value);
			WebElement button=webDriver.findElement(By.xpath("//input[@type='submit']"));
			button.click();
			WebElement ano=webDriver.findElement(By.xpath("//div[@class='trans-container']"));
//			System.out.println(ano.getText()); 
			String answer=ano.getText();
			webDriver.close();
			return new ResponseMsg(1, "", answer).toJson();
		} catch (Exception e) {
			return new ResponseMsg(0, e.toString(), "").toJson();
		}
	}
}
