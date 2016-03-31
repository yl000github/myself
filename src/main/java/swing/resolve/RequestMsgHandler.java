package swing.resolve;

import web.message.ResponseMsg;

public class RequestMsgHandler implements IResolve{

	@Override
	public String resolve(RequestMsg request) {
		if(request==null) return fail("request为空");
		if(request.getContent()==null) return fail("content为空");
		if(request.getDomain()==null) return fail("domain为空");
		if(request.getTicket()==null) return fail("ticket为空");
		if(request.getSrc()==null) return fail("src为空");
		if(!auth(request.getDomain(),request.getTicket())){
			return fail("认证失败");
		}
		if(request.getSrc().equals("app")){
			RequestMsgContent rmc=request.getContent();
			//TODO
			
			
			
		}
		
		return null;
	}
	private boolean auth(String domain, String ticket) {
		if(domain.equals("admin")&&ticket.equals("123456")) return true;
		return false;
	}
	public String fail(String msg){
		return new ResponseMsg(0,msg,null).toJson();
	}

}
