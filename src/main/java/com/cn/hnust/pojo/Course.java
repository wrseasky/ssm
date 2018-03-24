package com.cn.hnust.pojo;

import java.util.List;

public class Course {
	private Integer id;
	private String courseName;// 课程名称
	private List<Student> students;// 选课学生

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
