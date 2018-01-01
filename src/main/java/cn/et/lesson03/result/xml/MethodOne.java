package cn.et.lesson03.result.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class MethodOne {
	public static SqlSession getSession() {
		String resource = "cn/et/lesson03/result/xml/mybatis.xml";
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
	
	@Test
	public void testQueryGrade() {
		SqlSession session = getSession();
		GradeMapper mapper = session.getMapper(GradeMapper.class);
		List<Grade> queryAllGrade = mapper.queryAllGrade();
		for (Grade grade : queryAllGrade) {
			System.out.println(grade.getGid()+"----"+grade.getGname1());
		}
	}
	
	/**
	 * mybatis懒加载需要配置:
	 * 		fetchType="lazy"
	 * 	关联的对象是访问其属性时才加载
	 */
	@Test
	public void testQueryStudent() {
		SqlSession session = getSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		Student queryStudent = mapper.queryStudent("6");
		System.out.println(queryStudent.getSname());
//		System.out.println(queryStudent.getGrade().getGname1());
//		System.out.println(queryStudent.getSname()+"----"+queryStudent.getGrade().getGname1());
	}
	
	
	@Test
	public void testQueryGradeMany() {
		SqlSession session = getSession();
		GradeMapper mapper = session.getMapper(GradeMapper.class);
		Grade queryGrade = mapper.queryGrade("2");
		for(Student st: queryGrade.getStudentList()) {
			System.out.println(st.getSname());
		}
	}
}
