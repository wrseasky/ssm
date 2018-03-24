package com.cn.hnust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cn.hnust.pojo.Classes;
import com.cn.hnust.pojo.Course;
import com.cn.hnust.pojo.Student;
import com.cn.hnust.service.ClassesService;
import com.cn.hnust.service.CourseService;
import com.cn.hnust.service.StudentService;

@RestController
public class StudentClassesCourseController {
	@Autowired
	private StudentService studentService;

	
	// ============ 一对多中 多 的一方的增删改查 ===============
	
	
	@RequestMapping("/select")
	public Student t() {
		Student student = studentService.selectByPrimaryKey(1);
		System.out.println(student);
		return student;
	}

	@RequestMapping("/update")
	public void tt() {
		Student student = studentService.selectByPrimaryKey(1);
		student.setSname("update");
		int updateByPrimaryKeySelective = studentService.updateByPrimaryKeySelective(student);
		System.out.println(updateByPrimaryKeySelective);
	}

	@RequestMapping("/delete")
	public void ttt() {
		int deleteByPrimaryKey = studentService.deleteStudentById(2);
		System.out.println(deleteByPrimaryKey);
	}

	@RequestMapping("/insert")
	public void tttt() {
		Student student = new Student();
		student.setSname("add student");
		student.setVersion(1);
		Classes classes = new Classes();
		classes.setId(13);
		student.setClasses(classes);
		int insert = studentService.insert(student, 2);
		System.out.println(insert);

		System.out.println("新增后的id: " + student.getSid());
	}

	
	
	// ============ 一对多中 一 的一方的增删改查 ===============

	@Autowired
	private ClassesService classesService;
	
	
	@RequestMapping("/selectone")
	public Classes z() {
		Classes classes = classesService.selectByPrimaryKey(13);
		System.out.println(classes);
		return classes;
	}

	@RequestMapping("/updateone")
	public Integer zz() {
		Classes classes = classesService.selectByPrimaryKey(13);
		classes.setName("update");
		int updateByPrimaryKeySelective = classesService.updateByPrimaryKeySelective(classes);
		System.out.println(updateByPrimaryKeySelective);
		return updateByPrimaryKeySelective;
	}

	@RequestMapping("/deleteone")
	public void zzz() {
		int deleteByPrimaryKey = classesService.deleteByPrimaryKey(2);
		System.out.println(deleteByPrimaryKey);
	}

	@RequestMapping(value = "/insertone",produces = {"application/json;charset=UTF-8"})
	public String zzzz() {
		Classes classes = new Classes();
		classes.setName("insert2");
		classes.setVersion(1);
		int insert = classesService.insert(classes);
		System.out.println(insert);

		return "新增后的id: " + classes.getId();
	}
	
	//===============  course  ====================
	@Autowired
	private CourseService courseService;
	@RequestMapping("/selectname")
	public List<Course> u() {
		List<Course> courses = courseService.selectByName("语");
		return courses;
	}
}
