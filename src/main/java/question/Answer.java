package question;

public class Answer {
	String introduction;
	String content;
	String date;
	String link;
	public Answer(String introduction,String content,String link,String date) {
		this.introduction=introduction;
		this.content=content;
		this.link=link;
		this.date=date;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String toString(){
		return "简介:"+introduction+"	内容:"+content+"		链接:"+link+"		日期:"+date;
	}
}
