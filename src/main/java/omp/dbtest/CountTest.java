package omp.dbtest;

import java.io.Reader;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.QuotationMapper;
import domain.CountUserOrderAdd;
import domain.Quotation;
import utils.JsonUtil;

public class CountTest {

	SqlSession sqlSession;
	@Before
	public void b(){
		String resource = "mybatis-config.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println(e.getMessage());
        }
	}
	@After
	public  void a(){
		sqlSession.close();
	}
	public int r(int top){
		return (int)(Math.random()*top)+1;
	}
	@Test
	public void insert(){
		CountUserOrderAdd c=new CountUserOrderAdd();
		sqlSession.commit();
	}
	@Test
	public void all(){
		QuotationMapper mapper=sqlSession.getMapper(QuotationMapper.class);
		String s=JsonUtil.ob2json(mapper.selectByPrimaryKey(1));
		System.out.println(s);
	}

}
