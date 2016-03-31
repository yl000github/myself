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
			String type=rmc.getType();
			String instruct=rmc.getInstruct();
			if(type.equals("00")){
				//test case
				return new ResponseMsg(1,null,instruct).toJson();
			}else if(type.equals("01")){
				//front cmd
			}else if(type.equals("02")){
				//cmd
			}else if(type.equals("03")){
				//qq
			}else if(type.equals("04")){
				//others
			}else {
				return fail("type值不对");
			}
		}
		
		return fail("不正常状态");
	}
	private boolean auth(String domain, String ticket) {
		if(domain.equals("admin")&&ticket.equals("123456")) return true;
		return false;
	}
	public String fail(String msg){
		return new ResponseMsg(0,msg,null).toJson();
	}

}
