package swing.resolve;

public class RequestMsg {
	private String src;
	private String domain;
	private String ticket;
	private RequestMsgContent content;
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public RequestMsgContent getContent() {
		return content;
	}
	public void setContent(RequestMsgContent content) {
		this.content = content;
	}
	
}
