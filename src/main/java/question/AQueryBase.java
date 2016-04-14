package question;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import exception.ErrorException;

public abstract class AQueryBase implements IQuery{
	protected int ansNum=5;
	WebDriver driver=new HtmlUnitDriver();
	@Override
	public List<Answer> query(String question) throws ErrorException {
		return ask(driver,question);
	}
	protected void pause(int l){
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	protected void pause(){
		pause(5000);
	}
	public abstract List<Answer> ask(WebDriver driver,String question)  throws ErrorException ;
}
