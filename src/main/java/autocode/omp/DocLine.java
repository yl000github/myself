package autocode.omp;

public class DocLine {
	public String key;
	public String annotation;//注释
	public int flag;//0重要 1可忽略
	public DocLine(String key,String annotation,int flag){
		this.key=key;
		this.annotation=annotation;
		this.flag=flag;
	}
	public DocLine(String key,String annotation){
		this.key=key;
		this.annotation=annotation;
		this.flag=0;
	}
	public DocLine(String line){
		line=line.trim();
//		System.out.println(line);
		int f=line.indexOf(" ");//空格和制表符的区别
		int f1=line.indexOf("	");
		if(f==-1&&f1==-1) throw new RuntimeException("改行无法解析");
		if(f==-1) f=f1;
		else if(f1==-1) ;
		else f=f<f1?f:f1;
		int l=line.lastIndexOf("//");//空格和制表符的区别
		this.key=line.substring(0,f);
		this.annotation=line.substring(l,line.length());
		this.flag=0;
	}
}
