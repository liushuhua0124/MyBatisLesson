package cn.et.lesson05.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class TestMany {
	
	public SqlSessionFactory getSessionFactory() throws IOException {
		String resource = "cn/et/lesson05/xml/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	
	
	public static SqlSession getSession() {
		String resource = "cn/et/lesson05/xml/mybatis.xml";
		InputStream inputStream;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			//工厂类
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}
	
	/**
	 * 一级缓存:
	 * 		同一个session对象针对同一份数据的查询,产生的缓存
	 * 		第一次查询时,调用数据库,获取数据后通过session设置到一级缓存中
	 * 		第二次查询时,通过session一级缓存判断是否存在相同主键的数据值,如果存在,直接返回引用,否则查询数据库
	 * 		一级缓存不能跨session
	 * 		cacheEnabled是开控制二级缓存的,一级缓存是不能操作的
	 */
	@Test
	public void testMany() {
		SqlSession session = getSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		Student queryStudentByID = mapper.queryStudentByID("1");
		//从缓存中查询的
		Student queryStudentByID2 = mapper.queryStudentByID("1");
		System.out.println(queryStudentByID == queryStudentByID2);
	}
	
	
	/**
	 * 二级缓存:
	 * 	同一个sessionFactory下的不同session也可以共享数据
	 * @throws IOException
	 */
	@Test
	public void testQuery() throws IOException {
		SqlSessionFactory sessionFactory = getSessionFactory();
		SqlSession openSession = sessionFactory.openSession();
		SqlSession openSession2 = sessionFactory.openSession();
		
		StudentMapper mapper = openSession.getMapper(StudentMapper.class);
		Student queryStudentByID = mapper.queryStudentByID("1");
		openSession.close();
		
		StudentMapper mapper2 = openSession2.getMapper(StudentMapper.class);
		Student queryStudentByID2 = mapper2.queryStudentByID("1");
		
	}
	
}
