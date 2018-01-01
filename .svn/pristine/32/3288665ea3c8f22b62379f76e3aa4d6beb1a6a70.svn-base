package cn.et.lesson03.result.anno;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.lesson03.result.xml.Grade;
import cn.et.lesson03.result.xml.Student;

public class TestMrthodOne {
	public static SqlSession getSession() {
		String resource = "cn/et/lesson03/result/anno/mybatis.xml";
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
	
	@Test
	public void testManyToOne() {
		SqlSession session = getSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		Student queryStudent = mapper.queryStudent("2");
		System.out.println(queryStudent.getSname()+"---"+queryStudent.getGrade().getGname1());
	}
	
	
	@Test
	public void testManyTo() {
		SqlSession session = getSession();
		GradeMapper mapper = session.getMapper(GradeMapper.class);
		Grade queryGrade = mapper.queryGrade("2");
		for(Student query:queryGrade.getStudentList()) {
			System.out.println(query.getSname());
		}
	}
}
