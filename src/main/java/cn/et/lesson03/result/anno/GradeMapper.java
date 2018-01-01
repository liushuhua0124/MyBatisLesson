package cn.et.lesson03.result.anno;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.et.lesson03.result.xml.Grade;

public interface GradeMapper {
	
	@Results(
			@Result(column="gname",property="gname1")
	)
	@Select("select * from grade where gid = #{0}")
	public Grade queryGradeByGid(String gid);
	
	
	
	@Results({
			@Result(column="gname",property="gname1"),
			@Result(property="studentList",javaType=ArrayList.class,column="gid",many=@Many(select="cn.et.lesson03.result.anno.StudentMapper.queryStudentByGid"))
	})
	@Select("select * from grade where gid = #{0}")
	public Grade queryGrade(String gid);
}
