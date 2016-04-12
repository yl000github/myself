package autocode.omp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class ABase implements IOutput{
	String template;
	String interfaceName;
	List<DocLine> in=new ArrayList<>();
	//out 暂时不考虑
	String filePath="target/classes/autocode/omp/doc.txt";
	public ABase(){
		try {
			BufferedReader br=new BufferedReader(new FileReader(filePath));
			String line;
			int status=0;//0刚开始 1 入参 2出参
			while((line=br.readLine())!=null){
//				System.out.println(line);
				if(line.startsWith("URL:")){
					status=0;
					interfaceName=line.substring(line.indexOf("URL")+4, line.length());
					continue;
				}else if(line.startsWith("参数")){
					status=1;continue;
				}else if(line.startsWith("返回")){
					status=2;continue;
				};
				if(status==1){
					if(line.contains("{")||line.contains("}")) continue;
					DocLine l=new DocLine(line);
					in.add(l);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	protected String f(String pattern, Object ... arguments){
		return MessageFormat.format(pattern,arguments);
	}
	@Override
	public void print() {
		System.out.println(template);
	}
}
