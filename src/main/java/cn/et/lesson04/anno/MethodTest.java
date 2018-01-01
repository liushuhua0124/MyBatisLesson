package cn.et.lesson04.anno;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.lesson04.xml.Student;


public class MethodTest {
	public static SqlSession getSession() {
		String resource = "cn/et/lesson04/anno/mybatis.xml";
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
	public void testQuery() {
		SqlSession session = getSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		Student student = new Student();
//		student.setSname("张");
		List<Student> queryStudent = mapper.queryStudent(student);
		for (Student student2 : queryStudent) {
			System.out.println(student2.getSname());
		}
	}
	
	
	@Test
	public void updateQuery() {
		SqlSession session = getSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		Student student = new Student();
		student.setSid("1");
		student.setSname("张三");
		mapper.updateStudent(student);
		session.commit();
	}
	
	@Test
	public void QueryStudentAll() {
		SqlSession session = getSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		List<String> list = new ArrayList<String>();
		list.add("1");
//		list.add("2");
		List<Student> queryStudentByAnyGrade = mapper.queryStudentByAnyGrade(list);
		for (Student student : queryStudentByAnyGrade) {
			System.out.println(student.getSname());
		}
		session.commit();
	}
}
