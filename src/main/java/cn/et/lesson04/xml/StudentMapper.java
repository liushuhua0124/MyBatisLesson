package cn.et.lesson04.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface StudentMapper {
	//通过编号查询学生
	public List<Student> queryStudent(Student student);
	
	//根据性别来查所有的学生
	public List<Student> queryBySex(@Param("sex") String sex);
	
	//更新学生信息,没有返回值
	public void updateStudent(Student student);
	
	//通过传入的班级查询所有的学生
	public List<Student> queryStudentByAnyGrade(@Param("gradeList") List<String> gradeList);
}
