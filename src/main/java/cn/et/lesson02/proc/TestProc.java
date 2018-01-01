package cn.et.lesson02.proc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class TestProc {
	public static SqlSession getSession() {
		String resource = "cn/et/lesson02/proc/mybatis.xml";
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
	public void textproc() {
		SqlSession session = getSession();
		Map map = new HashMap();
		map.put("p1", 100);
		map.put("p2", 100);
		map.put("result", 0);
		String result = session.selectOne("proc.call_PRG_ADD",map);
		System.out.println(map.get("result"));
	}
	
	@Test
	public void textprocfun() {
		SqlSession session = getSession();
		Map map = new HashMap();
		map.put("p1", 100);
		map.put("p2", 200);
		map.put("result", 0);
		String result = session.selectOne("proc.call_FUN_ADD",map);
		System.out.println(map.get("result"));
	}
}
