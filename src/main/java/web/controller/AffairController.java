package web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.Affair;
import service.interfaces.IAffairService;

@Controller
public class AffairController {
	private transient static Log log=LogFactory.getLog(Affair.class);
	@Resource
	private IAffairService affairService;
	
	@RequestMapping(value="/affair/testPage")
	public String testPage(){
		return "/affair/test";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/affair/test",produces = {"text/plain;charset=utf-8"})
	public String test(){
		StringBuffer sb=new StringBuffer();
		List<Affair> list=affairService.getAll();
		for (Affair affair : list) {
			sb.append(affair.getWhat());
		}
		log.info(sb.toString());
		return sb.toString();
	}
	
	@RequestMapping(value="/affair/index")
	public String affairIndex(){
		return "/affair/affair";
	}
	
	
	
	
	
	
	
	
	
}
