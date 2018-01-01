package cn.et.lesson03.sql;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;




public class TestOne {
	
	public static SqlSession getSession() {
		String resource = "cn/et/lesson03/sql/mybatis.xml";
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
	
	public static void main(String[] args) throws IOException {
		
	}
	
	@Test
	public void testxmlInterfase() {
		SqlSession session = getSession();
		FoodMapper mapper = session.getMapper(FoodMapper.class);
		mapper.queryFood("永州血鸭", "500");
		session.commit();
	}
	
	@Test
	public void testquery() {
		SqlSession session = getSession();
		FoodMapper mapper = session.getMapper(FoodMapper.class);
		List queyFoodAll = mapper.queyFoodAll();
		System.out.println(queyFoodAll.size());
	}
}
