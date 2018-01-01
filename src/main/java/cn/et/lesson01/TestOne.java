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
			//������
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}
	
	public static void main(String[] args) throws IOException {
		SqlSession session = getSession();
		//session��������,ָ��sql����һ��Ψһ��ʶ��
		//�� = sql���
		List selectList = session.selectList("a.selectFood");
		System.out.println(selectList);
	}
	
	//�Զ���log4j��ӡ��־
	Logger logger = Logger.getLogger(TestOne.class);
	@Test
	public void testQueryByParam() {
		SqlSession session = getSession();
		//��map��ֵ
		/*Map map = new HashMap();
		map.put("price", 500);
		map.put("foodname", "�ཷ����");*/
		//�ö���ֵ
		Food food = new Food();
		food.setFoodname("����С��");
		food.setPrice("20");
		List<Object> selectList = session.selectList("a.selectFoodBy",food);
//		System.out.println(selectList);
		System.out.println(selectList);
	}
	
	@Test
	public void testsaveFoodParam() {
		SqlSession session = getSession();
		Map map = new HashMap();
		map.put("foodname", "����ѪѼ");
		map.put("price", 500);
		int insert = session.insert("a.saveFood",map);
		session.commit();
		System.out.println(insert);
	}
}
