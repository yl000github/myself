package web.controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import robot1.dao.QQSanGongDao;
import web.message.ResponseMsg;

@Controller
public class QQController {
	@ResponseBody
	@RequestMapping(value="/qqsangong/all",produces = {"text/plain;charset=utf-8"})
	public String qqsangong(){
		QQSanGongDao dao=new QQSanGongDao();
		try {
			List<Map<String,String>> l=new ArrayList<>();
			ResultSet rs=dao.queryAll();
			while(rs.next()){
				Map<String,String> m=new HashMap<>();
				m.put("id", rs.getString(1));
				m.put("what", rs.getString(2));
				m.put("create_time", rs.getString(3));
				l.add(m);
			}
			return new ResponseMsg(1,null,l).toJson();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(0,"产生异常",null).toJson();
		}
	}
}
