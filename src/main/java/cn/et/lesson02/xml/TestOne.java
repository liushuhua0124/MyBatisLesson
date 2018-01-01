package cn.et.lesson02.xml;

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
		String resource = "cn/et/lesson02/xml/mybatis.xml";
		InputStream inputStream;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			//π§≥ß¿‡
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
		mapper.queryFood("”¿÷›—™—º", "500");
		session.commit();
	}
	
	@Test
	public void testxmlInterfaseAll() {
		SqlSession session = getSession();
		FoodMapper mapper = session.getMapper(FoodMapper.class);
		List queryFoodname = mapper.queryFoodname("Ω∑");
		session.commit();
		System.out.println(queryFoodname);
	}
	
	@Test
	public void deletexmlInterfase() {
		SqlSession session = getSession();
		FoodMapper mapper = session.getMapper(FoodMapper.class);
		mapper.deleteFood("5");
		session.commit();
	}
	
	@Test
	public void saveexmlInterfaseAll() {
		SqlSession session = getSession();
		FoodMapper mapper = session.getMapper(FoodMapper.class);
		FoodAll food = new FoodAll();
		food.setFoodname("¬È¿±≥¥µ∞");
		food.setPrice("400");
		mapper.saveFood(food);
		session.commit();
		System.out.println(food.getFoodid());
	}
}
