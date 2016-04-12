package autocode.omp;

import java.io.File;

public class Interface extends ABase{
	String author="yanglin";
	String description="";
	CreateLogic createLogic=new CreateLogic();
	public Interface(){
		initFunc();
		String l=createLogic.getTemplate();
		template=template+"\n"+l;
	}
	private void initFunc() {
		StringBuffer sb=new StringBuffer();
//		sb.append("/**\n");sb.append("\n");
//		sb.append("*@author "+author);sb.append("\n");
//		sb.append("*@description "+description);sb.append("\n");
//		sb.append("*/");sb.append("\n");
//		sb.append("(function(request,header){");sb.append("\n");
		for (DocLine docLine : in) {
			String key=docLine.key;
			String annotation=docLine.annotation;
			sb.append(f("var {0}=request.{0}; {1}",key,annotation));sb.append("\n");
		}
//		sb.append("var lo = createLogic();");sb.append("\n");
//		sb.append("//登录校验");sb.append("\n");
//		sb.append("if(!AuthCheck.isLogin(request.ticket, request.domain)){");sb.append("\n");
//		sb.append("errorResponse(-1000, {logoutUrl : logoutUrl});");sb.append("\n");
//		sb.append("return;");sb.append("\n");
		template=sb.toString();
	}
	public static void main(String[] args) {
		IOutput o=new Interface();
		o.print();
//		File f=new File("");
//		System.out.println(f.getPath());
//		System.out.println(f.getAbsolutePath());
	}
}
