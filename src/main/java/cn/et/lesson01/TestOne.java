package cn.et.lesson01;

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
		String resource = "cn/et/lesson01/mybatis.xml";
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
		SqlSession session = getSession();
		//session操作的是,指向sql语句的一个唯一标识符
		//键 = sql语句
		List selectList = session.selectList("a.selectFood");
		System.out.println(selectList);
	}
	
	//自定义log4j打印日志
	Logger logger = Logger.getLogger(TestOne.class);
	@Test
	public void testQueryByParam() {
		SqlSession session = getSession();
		//用map传值
		/*Map map = new HashMap();
		map.put("price", 500);
		map.put("foodname", "青椒炒蛋");*/
		//用对象传值
		Food food = new Food();
		food.setFoodname("麻辣小炒");
		food.setPrice("20");
		List<Object> selectList = session.selectList("a.selectFoodBy",food);
//		System.out.println(selectList);
		System.out.println(selectList);
	}
	
	@Test
	public void testsaveFoodParam() {
		SqlSession session = getSession();
		Map map = new HashMap();
		map.put("foodname", "永州血鸭");
		map.put("price", 500);
		int insert = session.insert("a.saveFood",map);
		session.commit();
		System.out.println(insert);
	}
}
