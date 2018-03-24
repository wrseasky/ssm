package com.cn.hnust.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cn.hnust.pojo.Course;
import com.cn.hnust.service.CourseMapperAnnotationService;

@RestController
public class CourseMapperAnnotationController {
	@Autowired
	private CourseMapperAnnotationService courseMapperAnnotationService;

	@RequestMapping("/u")
	public int add() {
		Course course = new Course();
		course.setCourseName("annotation");
		int add = courseMapperAnnotationService.add(course);
		System.out.println("add id " + course.getId());
		return add;
	}

	@RequestMapping("/uu")
	public int deleteById() {
		return courseMapperAnnotationService.deleteById(10);
	}

	@RequestMapping("/uuu")
	public int update() {
		Course course = courseMapperAnnotationService.getCourseById(1);
		course.setCourseName("Chinese");
		return courseMapperAnnotationService.update(course);
	}

	@RequestMapping("/uuuu")
	public Course getCourseById() {
		return courseMapperAnnotationService.getCourseById(1);
	}

	@RequestMapping("/uuuuu")
	public List<Course> getAllCourses() {
		return courseMapperAnnotationService.getAllCourses();
	}

	//=======================  insertAll  selectCoursesByIds  ===========================  
	@RequestMapping("/z")
	public void courseMapperAnnotationService() {
		List<Course> courses = new ArrayList<Course>();
		for (int i = 0; i < 3; i++) {
			Course course = new Course();
			course.setCourseName("courseAll" + i);
			courses.add(course);
		}
		courseMapperAnnotationService.insertAllByAnnotation(courses);
	}
	
	@RequestMapping("/zz")
	public void insertAllByXml() {
		List<Course> courses = new ArrayList<Course>();
		for (int i = 0; i < 3; i++) {
			Course course = new Course();
			course.setCourseName("courseAll" + i);
			courses.add(course);
		}
		courseMapperAnnotationService.insertAllByXml(courses);
	}
	
	@RequestMapping("/zzz")
	public List<Course> selectCoursesByIds() {
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			ids.add(i);
		}
		return courseMapperAnnotationService.selectCoursesByIds(ids);
	}
}
