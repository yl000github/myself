package robot1.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


public class QQSanGongDao extends AJdbc{
	public QQSanGongDao(){
		super();
	}
	public void insert(String what)throws Exception{
		stmt=conn.createStatement();
		sql="insert into qqsangong values(NULL,'"+what+"',NOW())";
		stmt.execute(sql);
	}
	public ResultSet queryAll()throws Exception{
		stmt=conn.createStatement();
		sql="select * from qqsangong";
		rs=stmt.executeQuery(sql);
		return rs;
	}
	public static void main(String[] args) throws Exception {
		QQSanGongDao sgDao=new QQSanGongDao();
		ResultSet rs=sgDao.queryAll();
		//连续0或1  次数
		String b=null,c=null;
		int[] tj=new int[20];
		int sum=1;
		while(rs.next()){
			c=rs.getString(2);
			if(b==null){
				
			}else{
				if(b.equals(c)){
					sum++;
				}else{
					tj[sum]++;
					sum=1;
				}
			}
			b=c;
		}
		int ss=0;
		for (int i = 1; i < tj.length; i++) {
			ss+=tj[i]*i;
			System.out.println("连续"+i+"次一样出现的次数:"+tj[i]);
		}
		System.out.println("总数："+ss);
//		int [] tj=new int[20];
		
//		ResultSet rs=sgDao.queryAll();
//		sgDao.printResultSet(rs);
	}
}
