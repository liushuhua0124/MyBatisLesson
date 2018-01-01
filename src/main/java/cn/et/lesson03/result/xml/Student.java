package cn.et.lesson03.result.xml;

/**
 * 多对一:
 * 	多个学生属于一个班级
 * @author Administrator
 *
 */
public class Student {
	private String sid;
	private String sname;
	private String sex;
	private Grade grade;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

}
