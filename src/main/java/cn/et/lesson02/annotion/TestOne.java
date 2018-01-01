package cn.et.lesson02.annotion;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


public class TestOne {
	
	public static SqlSession getSession() {
		String resource = "cn/et/lesson02/annotion/mybatis.xml";
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
	public void testannotionInterfase() {
		SqlSession session = getSession();
		FoodMapper mapper = session.getMapper(FoodMapper.class);
		mapper.queryFood("麻辣小炒", "20");
		session.commit();
	}
	
	@Test
	public void testannotionInterfaseAll() {
		SqlSession session = getSession();
		FoodMapper mapper = session.getMapper(FoodMapper.class);
		List<Food> queryFoodAll = mapper.queryFoodAll("椒");
		session.commit();
		System.out.println(queryFoodAll);
	}
	
	@Test
	public void deleteannotionInterfase() {
		SqlSession session = getSession();
		FoodMapper mapper = session.getMapper(FoodMapper.class);
		mapper.deleteFood("5");
		session.commit();
	}
	
	@Test
	public void saveteannotionInterfaseAll() {
		SqlSession session = getSession();
		FoodMapper mapper = session.getMapper(FoodMapper.class);
		Food food = new Food();
		food.setFoodname("西红aaa炒蛋");
		food.setPrice("20");
		mapper.saveFood(food);
		session.commit();
		System.out.println(food.getFoodid());
	}
	
}
