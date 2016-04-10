package robot1.dao;

import java.sql.ResultSet;


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
		sgDao.insert("0");
		sgDao.insert("1");
		sgDao.insert("0");
		sgDao.insert("1");
		sgDao.insert("0");
		sgDao.insert("1");
		sgDao.insert("0");
		sgDao.insert("1");
		sgDao.insert("0");
		sgDao.insert("1");
		sgDao.insert("0");
		sgDao.insert("1");
//		ResultSet rs=sgDao.queryAll();
//		sgDao.printResultSet(rs);
	}
}
