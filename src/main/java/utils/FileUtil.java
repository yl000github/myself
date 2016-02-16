package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileUtil {
	public static void write(String filepath,String content){
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(filepath));
			bw.write(content);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String read(String filepath){
		try {
			BufferedReader br=new BufferedReader(new FileReader(filepath));
			StringBuffer sb=new StringBuffer();	
			String line=null;
			while((line=br.readLine())!=null){
				sb.append(line);
				sb.append("\r\n");
			}
//			System.out.println(sb.toString());
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public static void main(String[] args){
		String c="hello yang";
		String filepath="E:/y.txt";
		write(filepath, c);
		System.out.println(read(filepath));
	}
}
