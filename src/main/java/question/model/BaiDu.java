package question.model;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import exception.ErrorException;
import question.AQueryBase;
import question.Answer;
import question.IQuery;

public class BaiDu extends AQueryBase{
	String url="http://www.baidu.com";
	@Override
	public List<Answer> ask(WebDriver driver, String question) throws ErrorException {
		// TODO Auto-generated method stub
		driver.get(url);
		WebElement input = driver.findElement(By.xpath("/html//input[@id='kw']"));
		WebElement button = driver.findElement(By.xpath("/html//input[@type='submit']"));
		input.sendKeys(question);
		button.click();
//		pause(2000);
		List<Answer> ans=new ArrayList<>();
		for (int i = 0; i < ansNum; i++) {
			int t=i+1;
			WebElement w=driver.findElement(By.xpath("//div[@id='"+t+"']"));
			String intro=w.findElement(By.xpath("h3/a")).getText();
			String link=w.findElement(By.xpath("h3/a")).getAttribute("href");
			String content=w.findElement(By.xpath("div")).getText();
			Answer a=new Answer(intro,content,link,null);
			ans.add(a);
		}
		return ans;
	}
	public static void main(String[] args) throws ErrorException {
		IQuery query=new BaiDu();
		String question="csdn";
		List<Answer> ans=query.query(question);
		for (Answer answer : ans) {
			System.out.println(answer.toString());
		}
	}
}
