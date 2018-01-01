package cn.et.lesson03.result.anno;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.et.lesson03.result.xml.Student;

public interface StudentMapper {
	//ע��������Ǵ�����
	@Results({
		@Result(property="grade",column="gid",one=@One(select="cn.et.lesson03.result.anno.GradeMapper.queryGradeByGid"))
	})
	@Select("select * from student where sid = #{0}")
	public Student queryStudent(String sid);
	
	
	
	@Select("select * from student where gid = #{0}")
	public List<Student> queryStudentByGid(String gid);
}
