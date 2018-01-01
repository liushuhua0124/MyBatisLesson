package cn.et.lesson03.result.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * 一对多: 一个班级有多个学生
 * 
 * @author Administrator
 * 
 */
public class Grade {
	private String gid;
	private String gname1;
	private List<Student> studentList = new ArrayList<Student>();

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGname1() {
		return gname1;
	}

	public void setGname1(String gname1) {
		this.gname1 = gname1;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

}
