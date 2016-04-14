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

public class Google extends AQueryBase{
	String url="http://www.google.com";
	@Override
	public List<Answer> ask(WebDriver driver, String question) throws ErrorException {
		driver.get(url);
//		WebElement html=driver.findElement(By.xpath("/html"));
		WebElement input = driver.findElement(By.xpath("/html//input[@id='lst-ib']"));
//		WebElement input = driver.findElement(By.xpath("/html//input"));
		WebElement button = driver.findElement(By.xpath("/html//input[@type='submit']"));
		input.sendKeys(question);
		button.click();
//		pause(2000);
		List<Answer> ans=new ArrayList<>();
		List<WebElement> ws=driver.findElements(By.xpath("//div[@class='g']"));
		int i=0;
		for (WebElement w : ws) {
			while(++i>ansNum) break;
			String intro=w.findElement(By.xpath("div/h3/a")).getText();
			String link=w.findElement(By.xpath("div/h3/a")).getAttribute("href");
			String content=w.findElement(By.xpath("div//span")).getText();
			Answer a=new Answer(intro,content,link,null);
			ans.add(a);
		}
		return ans;
	}
	public static void main(String[] args) throws ErrorException {
		IQuery query=new Google();
		String question="csdn";
		List<Answer> ans=query.query(question);
		for (Answer answer : ans) {
			System.out.println(answer.toString());
		}
	}
}
