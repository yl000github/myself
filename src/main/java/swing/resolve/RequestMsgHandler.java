package swing.resolve;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import exception.EmptyException;
import swing.ControlSystem;
import utils.LogUtil;
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
				CmdResolver.runFrontCmd();
				return success("成功");
			}else if(type.equals("02")){
				//cmd
				CmdResolver.runCmd(instruct);
				return success("成功");
			}else if(type.equals("03")){
				//common instruct
				if(instruct.equals("qq sg start")){
					LogUtil.logDaily("sangong start");
					ControlSystem.getHelper().sanGongStart();
					return success("操作成功");
				}else if(instruct.equals("qq sg stop")){
					LogUtil.logDaily("sangong stop");
					ControlSystem.getHelper().sanGongStop();
					return success("操作成功");
				}else if(instruct.equals("qq cy start")){
					ControlSystem.getHelper().chengyuStart();
					return success("操作成功");
				}else if(instruct.equals("qq cy stop")){
					ControlSystem.getHelper().chengyuStop();
					return success("操作成功");
				}else {
					return fail("暂不支持的指令");
				}
			}else if(type.equals("04")){
				//lunxun get one
				try {
					String msg=MsgQueue.getMsg();
					return success(msg);
				} catch (EmptyException e) {
					e.printStackTrace();
					return fail(e.getMessage());
				}
			}else if(type.equals("05")){
				//lunxun get all
				try {
					List<String> msg=MsgQueue.getAllMsg();
					return new ResponseMsg(1,null,msg).toJson();
				} catch (EmptyException e) {
//					e.printStackTrace();
					return success(e.getMessage());
				}
			}else{
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
	public String success(String msg){
		return new ResponseMsg(1,null,msg).toJson();
	}
	

}
