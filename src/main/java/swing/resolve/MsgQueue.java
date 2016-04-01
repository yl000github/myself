package swing.resolve;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import exception.EmptyException;

public class MsgQueue {
	public static MsgQueue instance;
	private static Queue<String> queue=new LinkedList<>();
	private MsgQueue(){
		
	}
	public static MsgQueue getInstance(){
		if(instance==null){
			instance=new MsgQueue();
		}
		return instance;
	}
	public static void addMsg(String msg){
		queue.add(msg);
	}
	public static String getMsg() throws EmptyException{
		if(queue.isEmpty()) throw new EmptyException("queue为空");
		return queue.poll();
	}
	public static List<String> getAllMsg() throws EmptyException{
		if(queue.isEmpty()) throw new EmptyException("queue为空");
		List<String> l=new ArrayList<>();
		while(!queue.isEmpty()){
			l.add(queue.poll());
		}
		return l;
	}
}
