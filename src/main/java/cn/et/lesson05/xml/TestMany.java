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
			//������
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}
	
	/**
	 * һ������:
	 * 		ͬһ��session�������ͬһ�����ݵĲ�ѯ,�����Ļ���
	 * 		��һ�β�ѯʱ,�������ݿ�,��ȡ���ݺ�ͨ��session���õ�һ��������
	 * 		�ڶ��β�ѯʱ,ͨ��sessionһ�������ж��Ƿ������ͬ����������ֵ,�������,ֱ�ӷ�������,�����ѯ���ݿ�
	 * 		һ�����治�ܿ�session
	 * 		cacheEnabled�ǿ����ƶ��������,һ�������ǲ��ܲ�����
	 */
	@Test
	public void testMany() {
		SqlSession session = getSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		Student queryStudentByID = mapper.queryStudentByID("1");
		//�ӻ����в�ѯ��
		Student queryStudentByID2 = mapper.queryStudentByID("1");
		System.out.println(queryStudentByID == queryStudentByID2);
	}
	
	
	/**
	 * ��������:
	 * 	ͬһ��sessionFactory�µĲ�ͬsessionҲ���Թ�������
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
