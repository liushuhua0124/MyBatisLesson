package cn.et.lesson04.anno;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import cn.et.lesson04.xml.Student;

public interface StudentMapper {
	
	static class queryStudentProvider {
		/**
		 * ${}在注解情况下会失效
		 * 对象的属性必须取值是别名.属性名
		 * 提供sql方法的参数是map键值对
		 * @param map
		 * @return
		 */
		public String queryStudent(Map map) {
			Student s = (Student)map.get("stu");
			String sql = "select * from student where 1=1 ";
			if(s.getSname()!=null && !"".equals(s.getSname())){
				s.setSname("%"+s.getSname()+"%");
				sql+=" and sname like #{stu.sname}";
			}
			if(s.getSex()!=null && !"".equals(s.getSex())){
				s.setSex("%"+s.getSex()+"%");
				sql+=" and sex like #{stu.sex}";
			}
			return sql;
		}
		
		public String queryStudentOne(Map map) {
			Student s = (Student)map.get("stu");
			SQL sql = new SQL();
			sql.SELECT("*").FROM("student");
			if(s.getSname()!=null && !"".equals(s.getSname())){
				s.setSname("%"+s.getSname()+"%");
				sql.WHERE(" sname like #{stu.sname}");
			}
			if(s.getSex()!=null && !"".equals(s.getSex())){
				s.setSex("%"+s.getSex()+"%");
				sql.AND();
				sql.WHERE(" sex like #{stu.sex}");
			}
			return sql.toString();
		}
		
		
		public String updateStudent(Map map) {
			Student stu = (Student)map.get("stud");
			SQL sql = new SQL();
			sql.UPDATE("student");
			if(stu.getSname()!=null && !"".equals(stu.getSname())) {
				sql.SET(" sname = #{param1.sname}");
			}
			if(stu.getAge()!=null && !"".equals(stu.getAge())) {
				sql.SET(" age = #{param1.age}");
			}
			sql.WHERE(" sid = #{param1.sid}");
			return sql.toString();
		}
		
		
		public String updateStudentAll(Map map) {
			List stu = (List)map.get("gid");
			SQL sql = new SQL();
			sql.SELECT("*").FROM("student");
			if(stu != null) {
				for (Object object : stu) {
					sql.WHERE(" gid in ("+ object + ")");
				}
			}
			return sql.toString();
		}
	}
	
	
	@SelectProvider(type = queryStudentProvider.class, method = "queryStudentOne")
	public List<Student> queryStudent(@Param("stu") Student student);
	
	
	
	
	//更新学生信息,没有返回值
	@UpdateProvider(type = queryStudentProvider.class,method="updateStudent")
	public void updateStudent(@Param("stud") Student student);
	
	
	//通过传入的班级查询所有的学生
	@SelectProvider(type = queryStudentProvider.class,method="updateStudentAll")
	public List<Student> queryStudentByAnyGrade(@Param("gid") List<String> gid);
}
