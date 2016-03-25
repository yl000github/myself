package swing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpServer implements Runnable{
	private ServerSocket serverSocket;
	private boolean isRunning=true;
	Request request=new Request();
	String response="ok";
	public HttpServer() {
		try {
			serverSocket=new ServerSocket(17777);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void start(){
		isRunning=true;
		new Thread(this).start();
	}
	public void stop(){
		isRunning=false;
	}
	@Override
	public void run() {
		while (isRunning) {//一直监听，直到受到停止的命令  
            Socket socket = null;  
            try {  
                socket = serverSocket.accept();//如果没有请求，会一直hold在这里等待，有客户端请求的时候才会继续往下执行  
                // log  
                BufferedReader bufferedReader = new BufferedReader(  
                        new InputStreamReader(socket.getInputStream()));//获取输入流(请求)  
                StringBuilder sb = new StringBuilder();  
                String line = null;  
                while ((line = bufferedReader.readLine()) != null  
                        && !line.equals("")) {//得到请求的内容，注意这里作两个判断非空和""都要，只判断null会有问题  
                    sb.append(line).append("/n");
                    parser(line);
                }  
                String content=sb.toString();
                // echo  
                PrintWriter printWriter = new PrintWriter(  
                        socket.getOutputStream(), true);//这里第二个参数表示自动刷新缓存  
                handler(content);
                
                doEcho(printWriter, response);//将日志输出到浏览器  
                System.out.println("********************");
                System.out.println(content);
                printWriter.flush();
                printWriter.close();
                bufferedReader.close();  
                socket.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
	}
	private void handler(String content) {
		//根据信息做解析
		if(request!=null){
			try {
				Map<String,String> params=request.getParams();
				String code=params.get("code");
				if(code.equals("01")){
					runCmd();
				}
			} catch (Exception e) {
				e.printStackTrace();
				response="解析出现问题";
			}
		}else{
			System.out.println("request为空");
			response="request为空";
		}
	}
    private void doEcho(PrintWriter printWriter, String record) {  
        printWriter.write(record);  
    } 
    /** 
     * 传入HTTP请求中需要解析的某一句 解析该句，并请放入对应的Request对象中 
     *  
     * @param s 
     */  
    private Request parser(String s) {  
        if (s.startsWith("GET")) {  
            String method = "Get";  
            request.setMethod(method);  
      
            int index = s.indexOf("HTTP");  
            System.out.println("index--->" + index);  
            String uri = s.substring(3 + 1, index - 1);// 用index-1可以去掉连接中的空格  
            System.out.println("uri--->" + uri);  
            request.setRequestURI(uri);  
            
            //获取get参数
            String raw=s.substring(s.indexOf("?")+1,index-1);
            String[] rs=raw.split("&");
            Map<String,String> map=new HashMap<>();
            try {
            	for (int i = 0; i < rs.length; i++) {
            		String t=rs[i];
            		String []rst=t.split("=");
            		map.put(rst[0], rst[1]);
            	}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("解析get参数失败");
			}
        	System.out.println("get参数---->"+map);
        	request.setParams(map);
            String protocol = s.substring(index);  
            System.out.println("protocol---->" + protocol);  
            request.setProtocol(protocol);  
        } else if (s.startsWith("POST")) {  
            String method = "POST";  
            request.setMethod(method);  
      
            int index = s.indexOf("HTTP");  
            System.out.println("index--->" + index);  
            String uri = s.substring(3 + 1, index - 1);// 用index-1可以去掉连接中的空格  
            System.out.println("uri--->" + uri);  
            request.setRequestURI(uri);  
      
            String protocol = s.substring(index);  
            System.out.println("protocol---->" + protocol);  
            request.setProtocol(protocol);  
      
        } else if (s.startsWith("Accept:")) {  
                String accept = s.substring("Accept:".length() + 1);  
            System.out.println("accept--->" + accept);  
            request.setAccept(accept);  
      
        } else if (s.startsWith("User-Agent:")) {  
            String agent = s.substring("User-Agent:".length() + 1);  
            System.out.println("agent--->" + agent);  
            request.setAgent(agent);  
      
        } else if (s.startsWith("Host:")) {  
            String host = s.substring("Host:".length() + 1);  
            System.out.println("host--->" + host);  
            request.setHost(host);  
      
        } else if (s.startsWith("Accept-Language:")) {  
            String language = s.substring("Accept-Language:".length() + 1);  
            System.out.println("language--->" + language);  
            request.setLanguage(language);  
      
        } else if (s.startsWith("Accept-Charset:")) {  
            String charset = s.substring("Accept-Charset:".length() + 1);  
            System.out.println("charset--->" + charset);  
            request.setCharset(charset);  
        } else if (s.startsWith("Accept-Encoding:")) {  
            String encoding = s.substring("Accept-Encoding:".length() + 1);  
            System.out.println("encoding--->" + encoding);  
            request.setEncoding(encoding);  
      
        } else if (s.startsWith("Connection:")) {  
            String connection = s.substring("Connection:".length() + 1);  
            System.out.println("connection--->" + connection);  
            request.setConnection(connection);  
        }  
        return request;
    } 
    
    public static void runCmd(){
    	Runtime r=Runtime.getRuntime();
    	String path="cmd.exe /C start C:\\Users\\Administrator\\Desktop\\omp\\front.bat";
    	Process pro = null;
    	try {
			pro = r.exec(path);
//		    InputStream in = pro.getInputStream();
//            int c;
//            while ((c = in.read()) != -1) {
//                System.out.print(c);// 如果你不需要看输出，这行可以注销掉
//            }
//            in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// pro.destroy();
    }
    public static void main(String[] args) {
    	System.out.println("hi");
    	runCmd();
	}
}
